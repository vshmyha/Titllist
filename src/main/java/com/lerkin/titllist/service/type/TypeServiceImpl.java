package com.lerkin.titllist.service.type;


import com.lerkin.titllist.dao.entity_db.TypeEntity;
import com.lerkin.titllist.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public List<TypeEntity> getTypes() {
        return typeRepository.findAll();
    }

    @Override
    public TypeEntity getTypeByAnimeId(Integer id) {
        return typeRepository.findTypeByAnimeId(id);
    }

}

