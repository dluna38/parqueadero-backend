package co.edu.iudigital.parqueadero.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;

import java.util.Map;

public class UtilParams {
    private  UtilParams() {
    }

    public static PageRequest getPageRequestFromMapParams(Map<String,String> params,String defaultFieldSort,Map<String,String> equivalenciasSort) throws PropertyReferenceException {
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
        if(equivalenciasSort !=null){
            String getSort = getSortBy(equivalenciasSort,params);
            if(getSort!=null) sortBy = getSort;
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
        //System.out.printf("PAGE REQUEST: page: %d | size: %d | sort: %s %n", request.getPageNumber(),request.getPageSize(),request.getSort());
        return request;
    }

    public static String getSortBy(Map<String,String> equivalencias,Map<String,String> params){
        if(params.containsKey("ordenarPor")){
            return equivalencias.get(params.get("ordenarPor").toLowerCase());
        }
        return null;
    }
}
