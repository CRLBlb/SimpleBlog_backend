package edu.hit.blogdemo.controller;

import edu.hit.blogdemo.pojo.*;
import edu.hit.blogdemo.result.Result;
import edu.hit.blogdemo.result.ResultFactory;
import edu.hit.blogdemo.service.BlogService;
import edu.hit.blogdemo.service.CollectionsService;
import edu.hit.blogdemo.service.CommentService;
import edu.hit.blogdemo.service.LikesService;
import edu.hit.blogdemo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    LikesService likesService;
    @Autowired
    CollectionsService collectionsService;
    @Autowired
    CommentService commentService;

    @CrossOrigin
    @GetMapping("/api/blog/{size}/{page}")
    //分页查询博文信息
    public Result listArticles(@PathVariable("size") int size
            , @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(blogService.list(page - 1, size));
    }

    @CrossOrigin
    @GetMapping("/api/blogbytitle/{size}/{page}")
    //分页根据文章标题查询博文信息
    public Result listArticlesByTitle(@PathVariable("size") int size
            , @PathVariable("page") int page
            ,@RequestParam("title") String title) {
        // 关键词为空时查询出所有博文
        if ("".equals(title)) {
            System.out.println("查询所有博文信息!");
            return ResultFactory
                    .buildSuccessResult(blogService.list(page - 1, size));
        }
        else {
            System.out.println("查询指定标题博文!");
            return ResultFactory
                    .buildSuccessResult(blogService.
                            findAllByTitleLike(page - 1, size,title));
        }
    }

//    @CrossOrigin
//    @GetMapping("/api/search")
//    //根据博文标题查询博文信息
//    public Result searchResult(@RequestParam("keyword") String keywords) {
//        // 关键词为空时查询出所有博文
//        if ("".equals(keywords)) {
//            System.out.println("查询所有信息!");
//            return ResultFactory
//                    .buildSuccessResult(blogService.list());
//        } else {
//            System.out.println("查询指定标题博文!");
//            return ResultFactory
//                    .buildSuccessResult(blogService.findAllByTitleLike(keywords));
//        }
//    }


    @CrossOrigin
    @GetMapping("/api/blog/{id}")
    //根据博文Id获取博文信息
    public Result getOneArticle(@PathVariable("id") int blogId) {
        return ResultFactory.buildSuccessResult(blogService.findByBlogId(blogId));
    }

    @CrossOrigin
    @PostMapping("/api/likeblog/{blogId}/{userId}")
    @ResponseBody
    //点赞博文
    public Result likeBlog(@PathVariable("blogId") int blogId
            ,@PathVariable("userId") int userId
            ,@RequestBody Likes likes ) {
        Blog blog = blogService.findByBlogId(blogId);
        //用户之前未点赞
        if(likesService.findByBlogIdAndUserId(blogId,userId) == null){
            //更新点赞数
            blog.setLikeNum(blog.getLikeNum()+1);
            //更新文章点赞数
            blogService.addOrUpdate(blog);
            //更新用户-点赞数据
            likesService.addOrUpdate(likes);
            System.out.println("成功点赞！");
            return ResultFactory.buildSuccessResult(blog);
        }
        //用户已点过赞
        else{
            //删除用户-点赞数据信息
            likesService.deleteUserLikes(likesService
                    .findByBlogIdAndUserId(blogId,userId).getLikeId());
            //文章点赞数-1
            blog.setLikeNum(blog.getLikeNum()-1);
            //更新文章点赞数
            blogService.addOrUpdate(blog);
            System.out.println("取消点赞！已删除点赞数据！");
            return ResultFactory.buildSuccessResult("取消点赞");
        }
    }



    @CrossOrigin
    @GetMapping("/api/checklikeblog/{blogId}/{userId}")
    @ResponseBody
    //点赞博文
    public Result checkLikeBlog(@PathVariable("blogId") int blogId
            ,@PathVariable("userId") int userId) {
        Blog blog = blogService.findByBlogId(blogId);
        //用户之前未点赞
        if(likesService.findByBlogIdAndUserId(blogId,userId) == null){
            //Do Nothing just Check
            return ResultFactory.buildSuccessResult("未点赞");
        }
        //用户已点过赞
        else{
            return ResultFactory.buildSuccessResult("已点赞");
        }
    }

    @CrossOrigin
    @GetMapping("/api/searchuserlikes")
    //查询用户-点赞数据
    public List<Likes> searchAllUserLikes() {
        return likesService.searchAllUserLikes();
    }








    @CrossOrigin
    @PostMapping("/api/collectionblog/{blogId}/{userId}")
    @ResponseBody
    //收藏博文
    public Result collectionBlog(@PathVariable("blogId") int blogId
            ,@PathVariable("userId") int userId
            ,@RequestBody Collections collections ) {
        Blog blog = blogService.findByBlogId(blogId);
        //用户之前未收藏
        if(collectionsService.findByBlogIdAndUserId(blogId,userId) == null){
            //更新收藏数
            blog.setCollectionNum(blog.getCollectionNum()+1);
            //更新文章收藏数
            blogService.addOrUpdate(blog);
            //更新用户-收藏数据
            collectionsService.addOrUpdate(collections);
            System.out.println("成功收藏！");
            return ResultFactory.buildSuccessResult(blog);
        }
        //用户已收藏
        else{
            //删除用户-收藏数据信息
            collectionsService.deleteUserCollections(collectionsService
                    .findByBlogIdAndUserId(blogId,userId).getCollectionId());
            //文章收藏数-1
            blog.setCollectionNum(blog.getCollectionNum()-1);
            //更新文章收藏数
            blogService.addOrUpdate(blog);
            System.out.println("取消收藏！已删除收藏数据！");
            return ResultFactory.buildSuccessResult("取消收藏");
        }
    }

    @CrossOrigin
    @GetMapping("/api/checkcollectionblog/{blogId}/{userId}")
    @ResponseBody
    //检测用户是否收藏信息
    public Result checkCollectionBlog(@PathVariable("blogId") int blogId
            ,@PathVariable("userId") int userId) {
        Blog blog = blogService.findByBlogId(blogId);
        //已登录用户之前未收藏过此文章
        if(collectionsService.findByBlogIdAndUserId(blogId,userId) == null){
            //Do Nothing just Check
            return ResultFactory.buildSuccessResult("未收藏");
        }
        //用户已收藏
        else{
            return ResultFactory.buildSuccessResult("已收藏");
        }
    }

    @CrossOrigin
    @GetMapping("/api/searchusercollections")
    //查询用户-收藏数据
    public List<Collections> searchAllUserCollections() {
        return collectionsService.searchAllUserCollections();
    }



    @CrossOrigin
    @GetMapping("/api/comment/{size}/{page}")
    //分页查询评论信息
    public Result listComments(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(commentService.list(page - 1, size));
    }
    @CrossOrigin
    @GetMapping("/api/comment/{id}")
    //根据commentId获取评论信息
    public Result getOneComment(@PathVariable("id") int commentId) {
        return ResultFactory.buildSuccessResult(commentService.findByCommentId(commentId));
    }



    //新增评论信息
    @CrossOrigin
    @PostMapping("/api/addcomment")
    @ResponseBody
    public Comment addUser(@RequestBody Comment comment){
        System.out.println("the result is:"+comment.getCommentId());
        System.out.println("the result is:"+comment.getCommentCreateTime());
        commentService.addOrUpdate(comment);
        return comment;
    }

    //ypy
    @CrossOrigin
    @GetMapping("/api/blog/all")
    public List<Blog> list() throws Exception{
        return blogService.alllist();
    }

    @CrossOrigin
    @PostMapping("/api/blog/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/vue/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder
                , StringUtils
                .getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @CrossOrigin
    @GetMapping("/api/blog/userid/{userid}")
    public List<Blog> list(@PathVariable("userid") int userid) throws Exception{
        return blogService.userlist(userid);
    }

    @CrossOrigin
    @PostMapping("/api/blog/add")
    public void add(@RequestBody Blog blog) throws Exception{
        blogService.add(blog);
    }

    @CrossOrigin
    @PostMapping("/api/blog/del/{blogid}")
    public void del(@PathVariable("blogid")int blogid) throws Exception{
        blogService.delete(blogid);
    }

    @CrossOrigin
    @GetMapping("/api/blog/blogid/{blogid}")
    public Blog find(@PathVariable("blogid")int blogid) throws Exception{
        return blogService.findById(blogid);
    }





}
