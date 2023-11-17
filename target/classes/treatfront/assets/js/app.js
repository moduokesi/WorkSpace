$(function () {
	"use strict";
	new PerfectScrollbar(".header-message-list"), new PerfectScrollbar(".header-notifications-list"), $(".mobile-search-icon").on("click", function () {
		$(".search-bar").addClass("full-search-bar")
	}), $(".search-close").on("click", function () {
		$(".search-bar").removeClass("full-search-bar")
	}), $(".mobile-toggle-menu").on("click", function () {
		$(".wrapper").addClass("toggled")
	}), $(".toggle-icon").click(function () {
		$(".wrapper").hasClass("toggled") ? ($(".wrapper").removeClass("toggled"), $(".sidebar-wrapper").unbind("hover")) : ($(".wrapper").addClass("toggled"), $(".sidebar-wrapper").hover(function () {
			$(".wrapper").addClass("sidebar-hovered")
		}, function () {
			$(".wrapper").removeClass("sidebar-hovered")
		}))
	}), $(document).ready(function () {
		$(window).on("scroll", function () {
			$(this).scrollTop() > 300 ? $(".back-to-top").fadeIn() : $(".back-to-top").fadeOut()
		}), $(".back-to-top").on("click", function () {
			return $("html, body").animate({
				scrollTop: 0
			}, 600), !1
		})
	}), $(function () {
		document.querySelector('.a1').checked = true; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
		document.querySelector('.topbar').style.background = sessionStorage.getItem('topbar-color');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = sessionStorage.getItem('sidebar-color')
		const id = sessionStorage.getItem('id')
		if (id !== null) {
			document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
		}
		if (id === '1') {
			$("html").addClass("color-header headercolor1"), $("html").removeClass("headercolor2 headercolor3 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		} else if (id === '2') {
			$("html").addClass("color-header headercolor2"), $("html").removeClass("headercolor1 headercolor3 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		} else if (id === '3') {
			$("html").addClass("color-header headercolor3"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		} else if (id === '4') {
			$("html").addClass("color-header headercolor4"), $("html").removeClass("headercolor1 headercolor2 headercolor3 headercolor5 headercolor6 headercolor7 headercolor8")
		} else if (id === '5') {
			$("html").addClass("color-header headercolor5"), $("html").removeClass("headercolor1 headercolor2 headercolor3 headercolor4 headercolor6 headercolor7 headercolor8")
		} else if (id === '6') {
			$("html").addClass("color-header headercolor6"), $("html").removeClass("headercolor1 headercolor3 headercolor4 headercolor5 headercolor2 headercolor7 headercolor8")
		} else if (id === '7') {
			$("html").addClass("color-header headercolor7"), $("html").removeClass("headercolor1 headercolor3 headercolor4 headercolor5 headercolor6 headercolor2 headercolor8")
		} else if (id === '8') {
			$("html").addClass("color-header headercolor8"), $("html").removeClass("headercolor1 headercolor3 headercolor4 headercolor5 headercolor6 headercolor7 headercolor2")
		}
		const ad = sessionStorage.getItem('ad')
		if (ad !== null) {
			document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
		}
		if (ad === '1') {
			$('html').addClass('color-sidebar sidebarcolor1');
		} else if (ad === '2') {
			$('html').addClass('color-sidebar sidebarcolor2');
		} else if (ad === '3') {
			$('html').addClass('color-sidebar sidebarcolor3');
		} else if (ad === '4') {
			$('html').addClass('color-sidebar sidebarcolor4');
		} else if (ad === '5') {
			$('html').addClass('color-sidebar sidebarcolor5');
		} else if (ad === '6') {
			$('html').addClass('color-sidebar sidebarcolor6');
		} else if (ad === '7') {
			$('html').addClass('color-sidebar sidebarcolor7');
		} else if (ad === '8') {
			$('html').addClass('color-sidebar sidebarcolor8');
		}
		const cd = sessionStorage.getItem('cd')
		if (cd === '1') {
			$("html").attr("class", "light-theme")
			document.querySelector('.a1').checked = true
			document.querySelector('.a2').checked = false
			document.querySelector('.a3').checked = false
			document.querySelector('.a4').checked = false
		} else if (cd === '2') {
			$("html").attr("class", "dark-theme")
			document.querySelector('.a1').checked = false
			document.querySelector('.a2').checked = true
			document.querySelector('.a3').checked = false
			document.querySelector('.a4').checked = false
		} else if (cd === '3') {
			$("html").attr("class", "semi-dark")
			document.querySelector('.a1').checked = false
			document.querySelector('.a2').checked = false
			document.querySelector('.a3').checked = true
			document.querySelector('.a4').checked = false
		} else if (cd === '4') {
			$("html").attr("class", "minimal-theme")
			document.querySelector('.a1').checked = false
			document.querySelector('.a2').checked = false
			document.querySelector('.a3').checked = false
			document.querySelector('.a4').checked = true
		}
		for (var e = window.location, o = $(".metismenu li a").filter(function () {
			return this.href == e
		}).addClass("").parent().addClass("mm-active"); o.is("li");) o = o.parent("").addClass("mm-show").parent("").addClass("mm-active")
	}), $(function () {
		$("#menu").metisMenu()
	}), $(".chat-toggle-btn").on("click", function () {
		$(".chat-wrapper").toggleClass("chat-toggled")
	}), $(".chat-toggle-btn-mobile").on("click", function () {
		$(".chat-wrapper").removeClass("chat-toggled")
	}), $(".email-toggle-btn").on("click", function () {
		$(".email-wrapper").toggleClass("email-toggled")
	}), $(".email-toggle-btn-mobile").on("click", function () {
		$(".email-wrapper").removeClass("email-toggled")
	}), $(".compose-mail-btn").on("click", function () {
		$(".compose-mail-popup").show()
	}), $(".compose-mail-close").on("click", function () {
		$(".compose-mail-popup").hide()
	}), $(".switcher-btn").on("click", function () {
		$(".switcher-wrapper").toggleClass("switcher-toggled")
	}), $(".close-switcher").on("click", function () {
		$(".switcher-wrapper").removeClass("switcher-toggled")
	}), $("#lightmode").on("click", function () {
		$("html").attr("class", "light-theme")
		document.querySelector('.topbar').style.background = '#fff'
		document.querySelector('.sidebar-wrapper').style.backgroundImage = ''
		sessionStorage.clear()
		sessionStorage.setItem('cd', 1)
	}), $("#darkmode").on("click", function () {
		$("html").attr("class", "dark-theme")
		document.querySelector('.topbar').style.background = '#171717'
		document.querySelector('.sidebar-wrapper').style.backgroundImage = ''
		sessionStorage.clear()
		sessionStorage.setItem('cd', 2)
	}), $("#semidark").on("click", function () {
		$("html").attr("class", "semi-dark")
		document.querySelector('.topbar').style.background = '#fff'
		document.querySelector('.sidebar-wrapper').style.backgroundImage = ''
		sessionStorage.clear()
		sessionStorage.setItem('cd', 3)
	}), $("#minimaltheme").on("click", function () {
		$("html").attr("class", "minimal-theme")
		document.querySelector('.topbar').style.background = '#fff'
		document.querySelector('.sidebar-wrapper').style.backgroundImage = ''
		sessionStorage.clear()
		sessionStorage.setItem('cd', 4)
	}), $("#headercolor1").on("click", function () {
		$("html").addClass("color-header headercolor1"), $("html").removeClass("headercolor2 headercolor3 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#0727d7';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#0727d7')
		sessionStorage.setItem('id', 1)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor2").on("click", function () {
		$("html").addClass("color-header headercolor2"), $("html").removeClass("headercolor1 headercolor3 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#23282c';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#23282c')
		sessionStorage.setItem('id', 2)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor3").on("click", function () {
		$("html").addClass("color-header headercolor3"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor5 headercolor6 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#e10a1f';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#e10a1f')
		sessionStorage.setItem('id', 3)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor4").on("click", function () {
		$("html").addClass("color-header headercolor4"), $("html").removeClass("headercolor1 headercolor2 headercolor3 headercolor5 headercolor6 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#157d4c';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#157d4c')
		sessionStorage.setItem('id', 4)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor5").on("click", function () {
		$("html").addClass("color-header headercolor5"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor3 headercolor6 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#673ab7';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#673ab7')
		sessionStorage.setItem('id', 5)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor6").on("click", function () {
		$("html").addClass("color-header headercolor6"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor5 headercolor3 headercolor7 headercolor8")
		document.querySelector('.topbar').style.background = '#795548';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#795548')
		sessionStorage.setItem('id', 6)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor7").on("click", function () {
		$("html").addClass("color-header headercolor7"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor5 headercolor6 headercolor3 headercolor8")
		document.querySelector('.topbar').style.background = '#d3094e';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#d3094e')
		sessionStorage.setItem('id', 7)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}), $("#headercolor8").on("click", function () {
		$("html").addClass("color-header headercolor8"), $("html").removeClass("headercolor1 headercolor2 headercolor4 headercolor5 headercolor6 headercolor7 headercolor3")
		document.querySelector('.topbar').style.background = '#ff9800';
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('topbar-color', '#ff9800')
		sessionStorage.setItem('id', 8)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	})



	// sidebar colors


	$('#sidebarcolor1').click(theme1);
	$('#sidebarcolor2').click(theme2);
	$('#sidebarcolor3').click(theme3);
	$('#sidebarcolor4').click(theme4);
	$('#sidebarcolor5').click(theme5);
	$('#sidebarcolor6').click(theme6);
	$('#sidebarcolor7').click(theme7);
	$('#sidebarcolor8').click(theme8);

	function theme1() {
		$('html').attr('class', 'color-sidebar sidebarcolor1');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/1.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/1.png)')
		sessionStorage.setItem('ad', 1)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme2() {
		$('html').attr('class', 'color-sidebar sidebarcolor2');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/2.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/2.png)')
		sessionStorage.setItem('ad', 2)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme3() {
		$('html').attr('class', 'color-sidebar sidebarcolor3');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/3.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/3.png)')
		sessionStorage.setItem('ad', 3)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme4() {
		$('html').attr('class', 'color-sidebar sidebarcolor4');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/4.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/4.png)')
		sessionStorage.setItem('ad', 4)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme5() {
		$('html').attr('class', 'color-sidebar sidebarcolor5');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/5.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/5.png)')
		sessionStorage.setItem('ad', 5)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme6() {
		$('html').attr('class', 'color-sidebar sidebarcolor6');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/6.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/6.png)')
		sessionStorage.setItem('ad', 6)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme7() {
		$('html').attr('class', 'color-sidebar sidebarcolor7');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/7.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/7.png)')
		sessionStorage.setItem('ad', 7)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}

	function theme8() {
		$('html').attr('class', 'color-sidebar sidebarcolor8');
		document.querySelector('.sidebar-wrapper').style.backgroundImage = 'url(assets/images/bg-themes/8.png)'
		sessionStorage.removeItem('cd')
		sessionStorage.setItem('sidebar-color', 'url(assets/images/bg-themes/8.png)')
		sessionStorage.setItem('ad', 8)
		document.querySelector('.a1').checked = false; document.querySelector('.a2').checked = false; document.querySelector('.a3').checked = false; document.querySelector('.a4').checked = false
	}
	//	if ($('.a1111').length) {
	//		$('.toggle-icon').click()
	//	}
	if ($('.a4444').length) {
		$('.tap').css('display', 'block')
		$('.tap1').css('display', 'none')
		$('.tap2').css('display', 'none')
	}
	if ($('.a2222').length) {
		$('.tap').css('display', 'none')
		$('.tap1').css('display', 'block')
		$('.tap2').css('display', 'none')
	}
	if ($('.a3333').length) {
		$('.tap').css('display', 'none')
		$('.tap1').css('display', 'none')
		$('.tap2').css('display', 'block')
	}
	$('.tap').on('click', 'li', function (e) {
		$(e.target).addClass('active').siblings().removeClass('active')
		if ($(`.b${$('.tap .active').data('xl')}`).siblings().hasClass('abc')) {
			if ($(`.b1`).css('display') === 'none') {
				$(`.b1`).animate({ 'opacity': 'toggle' })
				$('.b2,.b3,.b4').css({ 'display': 'none' })
			}
		} else if ($(`.b${$('.tap .active').data('xl')}`).parent().hasClass('abc')) {
			if ($(`.b${$('.tap .active').data('xl')}`).css('display') === 'none') {
				$(`.b${$('.tap .active').data('xl')}`).animate({ 'opacity': 'toggle' }).siblings().css({ 'display': 'none' })
				$(`.b1`).css({ 'display': 'none' })
			}
		} else {
			if ($(`.b${$('.tap .active').data('xl')}`).css('display') === 'none') {
				$(`.b${$('.tap .active').data('xl')}`).animate({ 'opacity': 'toggle' }).siblings().css({ 'display': 'none' })
			}
		}
		$('.p-0 .active').html($(e.target).html())
		//		console.log(1);
	})
	$('.tap1').on('click', 'li', function (e) {
		$(e.target).addClass('active').siblings().removeClass('active')
		if ($(`.b${$('.tap1 .active').data('xl')}`).css('display') === 'none') {
			$(`.b${$('.tap1 .active').data('xl')}`).animate({ 'opacity': 'toggle' }).siblings().css({ 'display': 'none' })
		}
		$('.p-0 .active').html($(e.target).html())
	})
	$('.tap2').on('click', 'li', function (e) {
		$(e.target).addClass('active').siblings().removeClass('active')
		if ($(`.b${$('.tap2 .active').data('xl')}`).css('display') === 'none') {
			$(`.b${$('.tap2 .active').data('xl')}`).animate({ 'opacity': 'toggle' }).siblings().css({ 'display': 'none' })
		}
		$('.p-0 .active').html($(e.target).html())
	})
	//	$('.sidebar-wrapper').mouseenter(() => {
	//		$('.tap').css('opacity', 0)
	//	})
	//	$('.sidebar-wrapper').mouseleave(() => {
	//		$('.tap').css('opacity', 1)
	//	})
	const list1 = ['上传文件', '文件管理', '器官分割']
	const list2 = ['文件标注与测量', '标签管理']
	const list3 = ['虚拟展示', '文件渲染', '器官分离', '器官文件']
	const list4 = ['测量直径', '测量面积', '测量体积', '计数']
	const list5 = ['后处理脚本分析', '自定义数据分析']
	const list6 = ['模型列表', '添加模型']
	const list7 = ['标签列表', '添加标签']
	if ($('.c1').length) {
		$.each(list1, (index, item) => {
			$(`.tap2 li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c2').length) {
		$.each(list2, (index, item) => {
			$(`.tap1 li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c3').length) {
		$.each(list3, (index, item) => {
			$(`.tap li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c4').length) {
		$.each(list4, (index, item) => {
			$(`.tap li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c5').length) {
		$.each(list5, (index, item) => {
			$(`.tap1 li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c6').length) {
		$.each(list6, (index, item) => {
			$(`.tap1 li:nth-child(${index + 1})`).html(item)
		})
	} else if ($('.c7').length) {
		$.each(list7, (index, item) => {
			$(`.tap1 li:nth-child(${index + 1})`).html(item)
		})
	}
	//$('.b1,.b2,.b3,.b4').css('display', 'none')
});