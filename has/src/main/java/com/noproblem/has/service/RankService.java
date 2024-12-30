package com.noproblem.has.service;


import com.noproblem.has.entity.Project;
import com.noproblem.has.entity.Rank;
import com.noproblem.has.mapper.ProjectMapper;
import com.noproblem.has.mapper.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RankService {
    @Autowired
    private RankMapper rankMapper;
    public List<Rank> findRank() {
        return rankMapper.findRank();
    }
}
