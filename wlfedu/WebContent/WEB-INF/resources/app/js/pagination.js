$.fn.pagination = function(fnPageTurning){
	
	var $this = $(this);

	// 翻页初始化
	var pageCount = $this.data("pagecount");
	var current = $this.data("current");

	if (current == 1) $this.find(".prev").addClass("disabled");
	if (current == pageCount) $this.find(".next").addClass("disabled");
	$this.find("li[data-page="+ current + "]").addClass("active");

	// 向前翻页
	$this.find(".prev").not(".disabled").on("click", function(){
		fnPageTurning(current * 1 - 1);
	});

	// 向后翻页
	$(".pagination .next").not(".disabled").on("click", function(){
		fnPageTurning(current * 1 + 1);
	});

	// 页标签
	$(".pagination").on("click", "li[data-page]", function(){
		var target = $(this).data("page");
		if (target == current) return;

		fnPageTurning(target);
	});
}