/**
 * 表示错误消息。
 * 
 * @param messages 错误消息
 */
function showErrorMsg(messages) {
	var msgDiv = $(".errorDiv");
	msgDiv.html('');
	if ($.isArray(messages)) {
		$.each(messages, function(i, e){
			var item = $("<span class=\"callout callout-danger\"></span>").text('' + e + '');
			msgDiv.append(item);
		});
	} else {
		var item = $("<span class=\"callout callout-danger\"></span>").text('' + messages + '');
		msgDiv.append(item);
	}
}

/**
 * 确认对话框
 * 
 * @param msg 确认消息
 */
function showConfirm(msg, onOk, onCancel) {
	
	var popDiv = '<section>'
		+ '<div class="popupWrapper">'
		+ ' <div class="popupBox">' 
		+ '  <div class="textTip">确认</div>'
		+ '  <div class="txtTip">' + msg + '</div>'
		+ '  <div class="buttonBox">'
		+ '   <div style="color: white" class="btn btnLeft">确认</div>'
		+ '   <div style="color: white" class="btn btnRight">取消</div>'
		+ '  </div>'
		+ ' </div>'
		+ '</div>'
		+ '<div class="mask"></div>'
		+ '</section>';
	
	var $Confirm = $(popDiv).appendTo($('body'));

	// 确认处理
	$Confirm.delegate(".btnLeft", "click", function(){
		$Confirm.remove();
		if (onOk && $.isFunction(onOk)) {
			onOk();
		}
	});

	// 取消处理
	$Confirm.delegate(".btnRight", "click", function(){
		$Confirm.remove();
		if (onCancel && $.isFunction(onCancel)) {
			onCancel();
		}
	});
}

/**
 * 消息提示
 * 
 * @param msg 提示消息
 */
function showMessage(msg, onOk) {
	
	var popDiv = '<section>'
		+ '<div class="popupWrapper">'
		+ ' <div class="popupBox">' 
		+ '  <div class="textTip">提示消息</div>'
		+ '  <div class="txtTip">' + msg + '</div>'
		+ '  <div class="buttonBox text-center">'
		+ '   <div class="btn btn-default">确认</div>'
		+ '  </div>'
		+ ' </div>'
		+ '</div>'
		+ '<div class="mask"></div>'
		+ '</section>';
	
	var $Message = $(popDiv).appendTo($('body'));

	// 确认处理
	$Message.delegate(".buttonBox", "click", function(){
		$Message.remove();
		if (onOk && $.isFunction(onOk)) {
			onOk();
		}
	});
}

/**
 * 表单提交
 * 
 * @param url 提交URL
 * @param params 参数
 */
function formPost(url, params) {
	var action = App.ContextPath + url;
	var $form = $("<form method='post'></form>").attr('action', action);
	$form.appendTo($('body'));
	
	if (params) {
		$.each(params, function(name, value){
			if (/\n/.test(value)) {
				$form.append($("<textarea name='" + name + "'></textarea>").val(value));
			} else {
				$form.append($("<input name='" + name + "' />").val(value));
			}
		});
	}
	$form.submit();
	$form.remove();
}

/**
 * Ajax请求
 * 
 * @param url 提交URL
 * @param params 参数
 * @param onSucceed 成功时回调函数
 * @param onError 失败时回调函数
 */
function ajaxPost(url, params, onSuccess, onError, json) {
	
	var maskDiv = 
		'<section>'
		+ '<div class="popupWrapper">'
		+ ' <div class="popupBox">' 
		+ '  <div class="txtTip">处理中。。</div>'
		+ ' </div>'
		+ '</div>'
		+ '<div class="mask"></div>'
		+ '</section>';
	
	var $Mask = $(maskDiv).appendTo($('body'));

	var option = {
		type: 'POST',
		url: App.ContextPath + url,
		data: params,
		cache: false,
		dataType: 'json',
		success: function(data) {
			if (data.status) {
				if (onSuccess && $.isFunction(onSuccess)) {
					onSuccess(data);
				}
			} else {
				if (onError && $.isFunction(onError)) {
					onError(data);
				} else {
					if (data.timeout) {
						formPost("/Login/logout");
					} else {
						showErrorMsg((data.errorMsg) ? data.errorMsg : "请求失败。");
					}
				}
			}
		},
		error: function() {
			if (onError && $.isFunction(onError)) {
				onError(data);
			} else {
				showErrorMsg("请求失败。");
			}
		},
		complete: function() {
			$Mask.remove();
		}
	};

	if (json) option.contentType = "application/json";

	// 请求
	$.ajax(option);
}

/**
 * Ajax请求(JSON）
 * 
 * @param url 提交URL
 * @param params 参数
 * @param onSucceed 成功时回调函数
 * @param onError 失败时回调函数
 */
function ajaxJson(url, params, onSuccess, onError) {
	
	// 请求处理
	ajaxPost(url, params, onSuccess, onError, true);
}