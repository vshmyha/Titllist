package com.lerkin.titllist.service.type;

import com.lerkin.titllist.dao.entity.Type;
import com.lerkin.titllist.dao.type.TypeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TypeServiceImpl implements TypeService {

    private final TypeDao typeDao;

    @Override
    public List<Type> getTypes() {
        return typeDao.selectTypes();
    }

    @Override
    public Type getTypeByAnimeId(Integer id) {
        return typeDao.selectTypeByAnimeId(id);
    }

}

