package org.anwang.safe.server.safescan.controller.tx;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.anwang.safe.server.safescan.business.service.IEventLogService;
import org.anwang.safe.server.safescan.controller.tx.model.EventLogQueryDTO;
import org.anwang.safe.server.safescan.controller.tx.model.EventLogVO;
import org.anwang.safe.server.safescan.repository.EventLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "EventLog")
@RestController
public class EventLogController {

    @Autowired
    IEventLogService eventLogService;

    @ApiOperation("查询EventLog列表")
    @PostMapping("tx/eventlogs")
    public List<EventLogVO> list(@RequestBody EventLogQueryDTO dto){
        String transactionHash = dto.getTransactionHash();
        List<EventLogEntity> list = eventLogService.listByTxHash(transactionHash);
        return list.stream().map( eventLogEntity -> {
            EventLogVO vo = new EventLogVO();
            vo.from(eventLogEntity);
            return vo;
        }).collect(Collectors.toList());
    }

}
