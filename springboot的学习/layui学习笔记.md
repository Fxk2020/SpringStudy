# layui学习笔记

## 1.菜单

```html
<div id="center" class="" style="height: 790px">
    <div class="layui-panel" style="max-width: 15%;margin-left: 1%">
        <ul class="layui-menu" id="docDemoMenu1">
            <li lay-options="{id: 100}">
                <div class="layui-menu-body-title">我的主页</div>
            </li>
            <li lay-options="{id: 101}">
                <div class="layui-menu-body-title">
                    <a href="#">我学习的课程<span class="layui-badge-dot"></span></a>
                </div>
            </li>
            <li class="layui-menu-item-divider"></li>
            <li class="layui-menu-item-group layui-menu-item-down" lay-options="{type: 'group'}">
                <div class="layui-menu-body-title">
                    修改个人信息<i class="layui-icon layui-icon-up"></i>
                </div>
                <ul>
                    <li lay-options="{id: 1031}">修改昵称</li>
                    <li lay-options="{id: 1032}">
                        <div class="layui-menu-body-title">个性签名</div>
                    </li>
                    <li lay-options="{id: 1033}">
                        <div class="layui-menu-body-title">上传头像</div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>


</div>
```

```javascript
<!--菜单栏的引用-->
<script>
    layui.use('dropdown', function(){
        var dropdown = layui.dropdown;

        //菜单点击事件，其中 docDemoMenu1 对应的是菜单结构上的 id 指
        dropdown.on('click(docDemoMenu1)', function(options){
            var othis = $(this); //当前菜单列表的 DOM 对象
            //options 菜单列表的 lay-options 中的参数
            //获取options中的属性直接获取即可
            if (options.id === 100){
                console.log("100000")
            }else {
                console.log(options)
            }

        });
    });

</script>
```