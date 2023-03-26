package com.zbq.controller;


import com.zbq.domain.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return Arrays.asList(new FileDto("1","File1","/home/","1024M","20230325","Analyzed","Andy1"),
                new FileDto("2","File2","/home/","100M","20230325","Analyzed","Andy1"),
                new FileDto("3","File3","/home/","100M","20230325","Analyzed","Andy1"),
                new FileDto("4","File4","/home/","100M","20230325","To Analyze","Andy1"),
                new FileDto("5","File5","/home/","300M","20230325","To Analyze","Andy1")
        );
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public FileDto findById(Integer id){
        return new FileDto("1","zb","","","","","");
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
