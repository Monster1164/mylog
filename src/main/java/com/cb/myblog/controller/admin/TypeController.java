package com.cb.myblog.controller.admin;

import com.cb.myblog.pojo.Type;
import com.cb.myblog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private  TypeService typeService;

    /**
     * 获取所有分类
     * @param model
     * @param pagenum
     * @return
     */
    @GetMapping("/types")
    public String list(Model model,
                       @RequestParam(value = "pagenum",defaultValue = "1",required = false) int pagenum ){

        //分页
        Page<Type> page = PageHelper.startPage(pagenum, 10);
        List<Type> types = typeService.listType();

        PageInfo<Type> pageInfo = new PageInfo<>(types);

        model.addAttribute("pageInfo",pageInfo);

        return "admin/types";
    }

    /**
     * 返回增加分类页面
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){

        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 编辑 分类
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id ,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }


    /**
     * 增加分类
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());

        if(type1!=null){
            result.rejectValue("name","nameError","已经存在该分类");
        }

        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.saveType(type);

        if (t==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 编辑更新分类
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){


//        System.out.println("--"+ id);
//        System.out.println(type.getName());

        Type type1 = typeService.getTypeByName(type.getName());

        if(type1!=null){
            result.rejectValue("name","nameError","已经存在该分类");
        }

        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.updateType(id,type);

        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String detele(@PathVariable Long id,RedirectAttributes attributes){

        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/types";
    }
}
