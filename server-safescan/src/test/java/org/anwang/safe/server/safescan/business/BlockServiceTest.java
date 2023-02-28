package org.anwang.safe.server.safescan.business;

import org.anwang.safe.server.safescan.business.service.IBlockService;
import org.anwang.safe.server.safescan.repository.BlockEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import root.SpringTestCase;

import java.math.BigInteger;

public class BlockServiceTest extends SpringTestCase {

    @Autowired
    IBlockService blockService;

    @Test
    public void testSave(){
        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setNumber(BigInteger.ONE);
        blockService.save(blockEntity);
    }

}
