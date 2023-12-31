package co.edu.iudigital.parqueadero.services;

import co.edu.iudigital.parqueadero.exceptions.FieldRequiredException;
import co.edu.iudigital.parqueadero.exceptions.ResourceNotFoundException;
import co.edu.iudigital.parqueadero.exceptions.ValidationException;
import co.edu.iudigital.parqueadero.models.Estancia;
import co.edu.iudigital.parqueadero.models.Pago;
import co.edu.iudigital.parqueadero.models.Tarifa;
import co.edu.iudigital.parqueadero.repositories.PagoRepository;
import co.edu.iudigital.parqueadero.utils.PdfCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;
    private final EstanciaService estanciaService;
    private final TarifaService tarifaService;

    public PagoService(PagoRepository pagoRepository, EstanciaService estanciaService, TarifaService tarifaService) {
        this.pagoRepository = pagoRepository;
        this.estanciaService = estanciaService;
        this.tarifaService = tarifaService;
    }
    @Transactional
    public Pago savePago(Pago pago){
        Tarifa tarifa;

        validatePago(pago);

        Estancia estancia = estanciaService.findById(pago.getEstancia().getId()).orElseThrow(()-> new ResourceNotFoundException("Estancia"));

        Optional<Pago> idPagoBD = pagoRepository.findPagoByEstanciaId(estancia.getId());
        if(idPagoBD.isPresent()) throw new ValidationException("pago","Ya existe un pago para esa estancia, pago id existente: "+idPagoBD);

        if(estancia.getMinutosTotales() == null){
            estancia = estanciaService.saveEstanciaSalida(estancia.getId());
        }

        if(pago.getTarifa() != null) {
            if(pago.getTarifa().getId() == null) throw new FieldRequiredException("tarifa id");
            tarifa = tarifaService.findById(pago.getTarifa().getId()).orElseThrow(() ->new ResourceNotFoundException("Tarifa"));
        }else{
            tarifa = tarifaService.findMostRecentTarifa().orElseThrow(() ->new ResourceNotFoundException("Sin tarifas registradas"));
        }

        double minutosPorTarifa = Math.ceil((double) estancia.getMinutosTotales()/tarifa.getPorMinutos());
        pago.setCobroTotal((long) (minutosPorTarifa*tarifa.getTarifaValor()));
        Pago pagoSaved = pagoRepository.save(pago);
        pagoSaved.setEstancia(estancia);
        return pagoSaved;
    }

    private void validatePago(Pago pago) {
        if(pago.getEstancia() == null || pago.getEstancia().getId() == null){
            throw new FieldRequiredException("Estancia");
        }

    }

    public Pago getPagoEstanciaById(Long id) {
        return pagoRepository.findPagoByEstanciaId(id).orElseThrow(()->new ResourceNotFoundException("pago"));
    }

    public List<Pago> getAllPago() {
        return pagoRepository.findAll();
    }

    public byte[] getPdfPago(Long id) {
        Pago pago = pagoRepository.findDetailPagoById(id).orElseThrow(() -> new ResourceNotFoundException("pago"));
        return PdfCreator.generarPdfPago(pago);
    }
}
