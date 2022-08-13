//表单类
/*
    lable : 字段描述
    id : 字段id
 */
FormItem = function (lable, id) {
    this.lable = lable;
    this.id = id;
}

//表单工具类
FormUtil = function () {
    //方法一：验证字段非空
    this.isNotEmpty = function (FormItemArry) {
        for (var i = 0; i < FormItemArry.length; i++) {
            var FormItem = FormItemArry[i];
            var lable = FormItem.lable;
            var id = FormItem.id;
            var obj = document.getElementById(id);
            if (obj.value === '' || obj.value == null) {
                alert(lable + "不能为空，请重新输入！");
                obj.focus();
                return false;
            }
        }
        return true;
    }
    //方法二：验证两个字段的一致性
    this.isSame = function (FormItem1, FormItem2) {
        var lable1 = FormItem1.lable;
        var id1 = FormItem1.id;
        //根据id获取字段
        var obj1 = document.getElementById(id1);

        var lable2 = FormItem2.lable;
        var id2 = FormItem2.id;
        var obj2 = document.getElementById(id2);

        if (obj1.value !== obj2.value) {
            alert(lable1 + "和" + lable2 + "不一致，请重新输入！");
            obj1.value = "";
            obj2.value = "";
            obj1.focus();
            return false;
        }
        return true;
    }
}

//页面加载时初始化工具类
var $ = new FormUtil();