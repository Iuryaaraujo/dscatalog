package com.devsuperior.dscatalog.util;

import com.devsuperior.dscatalog.projections.IdProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//refatorando o método auxiliar para que fique genérico
public class Utils {
    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordenada, List<? extends IdProjection<ID>> desordenada) {

        Map<ID, IdProjection<ID>> map = new HashMap<>();
        for(IdProjection<ID> obj : desordenada){
            map.put(obj.getId(), obj);
        }

        List<IdProjection<ID>> result = new ArrayList<>();
        for(IdProjection<ID> obj : ordenada){
            result.add(map.get(obj.getId()));
        }

        return result;
    }
}
