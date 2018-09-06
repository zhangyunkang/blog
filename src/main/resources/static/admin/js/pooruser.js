var mditor;
var tale = new $.tale();
var attach_url = $('#attach_url').val();
// 每10分钟自动保存一次草稿
// var refreshIntervalId = setInterval("autoSave()", 10 * 60 * 1000);
Dropzone.autoDiscover = false;
//执行一个laydate实例
laydate.render({
    elem: '#outpoorDate' //指定元素
    ,type: 'datetime'
});
$(document).ready(function () {
    mditor = window.mditor = Mditor.fromTextarea(document.getElementById('md-editor'));

    // Tags Input
    $('#tags').tagsInput({
        width: '100%',
        height: '35px',
        defaultText: '请输入文章标签'
    });

    $('.toggle').toggles({
        on: true,
        text: {
            on: '开启',
            off: '关闭'
        }
    });

    $("#multiple-sel").select2({
        width: '100%'
    });

    $('div.allow-false').toggles({
        off: true,
        text: {
            on: '开启',
            off: '关闭'
        }
    });

    if($('#thumb-toggle').attr('thumb_url') != ''){
        $('#thumb-toggle').toggles({
            off: true,
            text: {
                on: '开启',
                off: '关闭'
            }
        });
        $('#thumb-toggle').attr('on', 'true');
        $('#dropzone').css('background-image', 'url('+ $('#thumb-container').attr('thumb_url') +')');
        $('#dropzone').css('background-size', 'cover');
        $('#dropzone-container').show();
    } else {
        $('#thumb-toggle').toggles({
            off: true,
            text: {
                on: '开启',
                off: '关闭'
            }
        });
        $('#thumb-toggle').attr('on', 'false');
        $('#dropzone-container').hide();
    }

    var thumbdropzone = $('.dropzone');

    // 缩略图上传
    $("#dropzone").dropzone({
        url: "/admin/attach/upload",
        filesizeBase:1024,//定义字节算法 默认1000
        maxFilesize: '10', //MB
        fallback:function(){
            tale.alertError('暂不支持您的浏览器上传!');
        },
        acceptedFiles: 'image/*',
        dictFileTooBig:'您的文件超过10MB!',
        dictInvalidInputType:'不支持您上传的类型',
        init: function() {
            this.on('success', function (files, result) {
                console.log("upload success..");
                console.log(" result => " + result);
                if(result && result.success){
                    var url = attach_url + result.payload[0].fkey;
                    console.log('url => ' + url);
                    thumbdropzone.css('background-image', 'url('+ url +')');
                    thumbdropzone.css('background-size', 'cover');
                    $('.dz-image').hide();
                    $('#thumbimg').val(url);
                }
            });
            this.on('error', function (a, errorMessage, result) {
                if(!result.success && result.msg){
                    tale.alertError(result.msg || '缩略图上传失败');
                }
            });
        }
    });

});

/*
 * 自动保存为草稿
 * */
function  autoSave() {
    // var content = mditor.value;
    // var title = $('#articleForm input[name=title]').val();
    // if (title != '' && content != '') {
    //     $('#content-editor').val(content);
    //     $("#articleForm #categories").val($('#multiple-sel').val());
    //     var params = $("#articleForm").serialize();
    //     var url = $('#articleForm #cid').val() != '' ? '/admin/article/modify' : '/admin/article/publish';
    //     tale.post({
    //         url: url,
    //         data: params,
    //         success: function (result) {
    //             if (result && result.success) {
    //                 $('#articleForm #cid').val(result.payload);
    //             } else {
    //                 tale.alertError(result.msg || '保存文章失败');
    //             }
    //         }
    //     });
    // }
}
/*将时间戳转换成日期格式*/
function timestampToTime(timestamp) {
    var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = date.getDate() + ' ';
    var h = date.getHours() + ':';
    var m = date.getMinutes() + ':';
    var s = date.getSeconds();
    return Y+M+D+h+m+s;
}
/**
 * 保存贫困户信息
 * @param status
 */
function subPoorUser(status) {
    var username = $('#poorUserForm input[name=username]').val();
    //var dateformat=timestampToTime(outpoorDate);
     //$('#outpoorDate').val(dateformat);
    var outpoorDate =  $('#outpoorDate').val();
    var date = new Date(outpoorDate);
    var time1 = Date.parse(date)/1000;
    var enjoyPolicy =  mditor.value;
    if (username == '') {
        tale.alertWarn('请输入贫困户姓名');
        return;
    }
    if (enjoyPolicy == '') {
        tale.alertWarn('请输入享受政策');
        return;
    }
    $('#enjoyPolicy-editor').val(enjoyPolicy);
    $("#poorUserForm #status").val(status);
    if(outpoorDate){
        $("#hidOutpoorDate").val(time1);
    }
    var params = $("#poorUserForm").serialize();
    var url = $('#poorUserForm #uid').val() != '' ? '/admin/pooruser/modify' : '/admin/pooruser/publish';
    tale.post({
        url:url,
        data:params,
        success: function (result) {
            if (result && result.success) {
                tale.alertOk({
                    text:'贫困户保存成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/pooruser';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '保存贫困户失败');
            }
        }
    });
}


function allow_comment(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_comment').val(false);
    }
    if (off == 1) {
        $('#allow_comment').val(true);
    }
}

function allow_ping(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_ping').val(false);
    }
    if (off == 1) {
        $('#allow_ping').val(true);
    }
}


function allow_feed(obj) {
    var this_ = $(obj);
    var on = this_.find('.toggle-on.active').length;
    var off = this_.find('.toggle-off.active').length;
    if (on == 1) {
        $('#allow_feed').val(false);
    }
    if (off == 1) {
        $('#allow_feed').val(true);
    }
}

function add_thumbimg(obj) {
    var this_ = $(obj);
    var on = this_.attr('on');
    console.log(on);
    if (on == 'true') {
        this_.attr('on', 'false');
        $('#dropzone-container').addClass('hide');
        $('#thumbImg').val('');
    } else {
        this_.attr('on', 'true');
        $('#dropzone-container').removeClass('hide');
        $('#dropzone-container').show();
    }
}