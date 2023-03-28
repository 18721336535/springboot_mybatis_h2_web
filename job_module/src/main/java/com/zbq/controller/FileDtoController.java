package com.zbq.controller;


import com.zbq.domain.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/file")
@ResponseBody
public class FileDtoController {

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public List<FileDto> findAll(){
        System.out.println("test");
        List<String> columns = new ArrayList<>(Arrays.asList("pl_1","pl_2","pl_3","pl_4","pl_5","pl_11","pl_21","pl_31","pl_41","pl_51",
                "pl_01","pl_02","pl_03","pl_04","pl_05","pl_101","pl_201","pl_301","pl_401","pl_501"));
        return Arrays.asList(new FileDto("1","sft_trade_summary","/home/bz123/","1024M","20230325","Analyzed","Andy1",columns),
                new FileDto("2","otc_trade_summary","/home/bz123/","100M","20230325","Analyzed","Andy1",columns),
                new FileDto("3","futures_trade_summary","/home/bz123/","100M","20230325","Analyzed","Andy1",columns),
                new FileDto("4","equity_trade_summary","/home/bz123/","100M","20230325","To Analyze","Andy1",columns),
                new FileDto("5","cpm_trade_summary","/home/bz123/","300M","20230325","To Analyze","Andy1",columns)
        );
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public FileDto findById(Integer id){
        List<String> columns = new ArrayList<>(Arrays.asList("pl_1","pl_2","pl_3","pl_4","pl_5","pl_11","pl_21","pl_31","pl_41","pl_51",
                "pl_01","pl_02","pl_03","pl_04","pl_05","pl_101","pl_201","pl_301","pl_401","pl_501"));
        return new FileDto("1","zb","","","","","",columns);
    }

    /**
     * 更新
     * @param FileDto
     */
    @RequestMapping("/updateFileDto")
    public void updateFileDto(@RequestBody FileDto FileDto){
        System.out.println(FileDto);
    }
}
