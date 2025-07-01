package com.softtech.service;

import com.softtech.entity.Torihiki;
import java.util.List;

public interface TorihikiService {
    List<Torihiki> getAllTorihiki();
    
    Torihiki getTorihikiById(String companyID);
    
    List<Torihiki> searchTorihiki(String keyword);
    
    void insertTorihiki(Torihiki torihiki);
    
    void updateTorihiki(Torihiki torihiki);
    
    void deleteTorihiki(String companyID);

    String generateNewCompanyId();
} 