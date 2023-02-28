package org.anwang.safe.server.syncprice.business;

import org.anwang.safe.server.syncprice.repository.GatePropEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import root.SpringTestCase;

import java.util.List;

public class GatePropServiceTest extends SpringTestCase {

    @Autowired
    IGatePropService gatePropService;

    @Test
    public void test(){
        List<GatePropEntity> list = gatePropService.list();
        list.forEach( gatePropEntity -> {
            System.out.println(gatePropEntity.getPropKey());
        } );
    }

}
