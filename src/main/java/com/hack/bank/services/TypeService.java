package com.hack.bank.services;

import com.hack.bank.models.Type;
import com.hack.bank.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;
    public String getType(){
        List<Type> type = typeRepository.findAll();
        if(type != null && !type.isEmpty())
            return type.get(0).getTypeCode();
        else
            return "VOICE";
    }

    public void setType(String typeCode) {
        Type type = new Type(1,typeCode);
        typeRepository.save(type);
    }
}
