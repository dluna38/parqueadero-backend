package co.edu.iudigital.parqueadero.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;

import java.util.Map;

public class UtilParams {
    private  UtilParams() {
    }

    public static PageRequest getPageRequestFromMapParams(Map<String,String> params,String defaultFieldSort) throws PropertyReferenceException {
        int page = 0;
        int size = 20;
        String sortBy=defaultFieldSort;
        Sort.Direction direction = Sort.Direction.DESC;

        if(params.containsKey("pag")){
            try {
                page = Integer.parseInt(params.get("pag"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(params.containsKey("tam")){
            try {
                size = Integer.parseInt(params.get("tam"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(params.containsKey("ordenarPor")){
            try {
                if(params.get("ordenarPor").equalsIgnoreCase("fe")){
                    sortBy = params.get("fechaEntrada");
                }else if(params.get("ordenarPor").equalsIgnoreCase("fs")){
                    sortBy = params.get("fechaSalida");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(params.containsKey("tOrden")){
            try {
                if(params.get("tOrden").equalsIgnoreCase("ASC")){
                    direction = Sort.Direction.ASC;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        PageRequest request = PageRequest.of(page,size,Sort.by(direction,sortBy));
        System.out.printf("PAGE REQUEST: page: %d | size: %d | sort: %s %n", request.getPageNumber(),request.getPageSize(),request.getSort());
        return request;
    }
}
