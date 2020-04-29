package com.cb.myblog.controller.admin;

import com.cb.myblog.pojo.Tag;
import com.cb.myblog.pojo.Type;
import com.cb.myblog.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取所有标签
     * @param model
     * @param pagenum
     * @return
     */
    @GetMapping("/tags")
    public String list(Model model,
                       @RequestParam(value = "pagenum",defaultValue = "1",required = false) int pagenum ){

        //分页
        Page<Tag> page = PageHelper.startPage(pagenum, 10);

        List<Tag> tags = tagService.listType();

        PageInfo<Tag> pageInfo = new PageInfo<>(tags);

        model.addAttribute("pageInfo",pageInfo);

        return "admin/tags";
    }

    /**
     * 返回增加标签页面
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){

        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 编辑 标签
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id ,Model model){
        model.addAttribute("tag",tagService.getType(id));
        return "admin/tags-input";
    }


    /**
     * 增加标签
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag type, BindingResult result, RedirectAttributes attributes){

        Tag type1 = tagService.getTypeByName(type.getName());

        if(type1!=null){
            result.rejectValue("name","nameError","已经存在该标签");
        }

        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagService.saveType(type);

        if (t==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 编辑更新分类
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){


//        System.out.println("--"+ id);
//        System.out.println(type.getName());

        Tag type1 = tagService.getTypeByName(type.getName());

        if(type1!=null){
            result.rejectValue("name","nameError","已经存在该分类");
        }

        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagService.updateType(id,type);

        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String detele(@PathVariable Long id,RedirectAttributes attributes){

        tagService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");

        return "redirect:/admin/tags";
    }
}
