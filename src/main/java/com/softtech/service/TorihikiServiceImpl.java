package com.softtech.service;

import com.softtech.entity.Torihiki;
import com.softtech.mappers.TorihikiMapper;
import com.softtech.service.TorihikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TorihikiServiceImpl implements TorihikiService {

    @Autowired
    private TorihikiMapper torihikiMapper;

    @Override
    public List<Torihiki> getAllTorihiki() {
        return torihikiMapper.selectAll();
    }

    @Override
    public Torihiki getTorihikiById(String companyID) {
        return torihikiMapper.selectByPrimaryKey(companyID);
    }

    @Override
    public List<Torihiki> searchTorihiki(String keyword) {
        return torihikiMapper.search(keyword);
    }

    @Override
    public void insertTorihiki(Torihiki torihiki) {
        String now = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
        torihiki.setInsertDate(now);
        torihiki.setUpdateDate(now);
        torihikiMapper.insert(torihiki);
    }

    @Override
    public void updateTorihiki(Torihiki torihiki) {
        String now = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
        torihiki.setUpdateDate(now);
        torihikiMapper.updateByPrimaryKey(torihiki);
    }

    @Override
    public void deleteTorihiki(String companyID) {
        torihikiMapper.deleteByPrimaryKey(companyID);
    }

    @Override
    public String generateNewCompanyId() {
        // 示例生成方式，可根据实际需求调整
        String maxId = torihikiMapper.getMaxCompanyId();
        if (maxId == null) {
            return "C00001";
        }
        int num = Integer.parseInt(maxId.substring(1)) + 1;
        return String.format("C%05d", num);
    }
}
