/**
 * @author Pinku Singhal
 */
function changeImageTab(imageUrl, tabName, selectedTab, className, totalTab) {
	var tabNm = "#" + tabName;
	$(tabNm).attr("src", imageUrl);
	for(var i=1; i<= totalTab; i++) {
		tabSelected = "#tab" + i;
		$(tabSelected).removeClass(className);
	}
	tabSelected = "#tab" + selectedTab;
	$(tabSelected).addClass(className);
}

function changeImageTab( selectedTab, className, totalTab) {
	for(var i=1; i<= totalTab; i++) {
		tabSelected = "#tab" + i;
		$(tabSelected).removeClass(className);
		tabSelected = "#liTab" + i;
		$(tabSelected).removeClass(className);
	}
	tabSelected = "#tab" + selectedTab;
	$(tabSelected).addClass(className);
	tabSelected = "#liTab" + selectedTab;
	$(tabSelected).addClass(className);
}

function changeLinkTab(url, name, tabName) {
	var urlNm = "#" + tabName;
	$(urlNm).attr("href", url);
	$(urlNm).html(name);
}


var isInternetExplorer = (navigator.appName.indexOf("Microsoft") != -1);
var topNavbarOptions = false;
var reviewsLoaded = false;

function countSpaces(obj){
	var iLength = obj.value.length;
	var strSpaces = obj.value.match(new RegExp("( )", "g"));
	var countSpaces = strSpaces ? strSpaces.length : 0;
	return countSpaces;
}

function countLineBreaks(obj){

	var iLength = obj.value.length;
	var strLineBreaks = obj.value.match(new RegExp("(\\n)", "g"));
	var countLineBreaks = strLineBreaks ? strLineBreaks.length : 0;
	return countLineBreaks;
}

function backToSection(backToURL, forceBackToURL){
	if(forceBackToURL == null) forceBackToURL = false;
	if(history.length > 1 && !forceBackToURL) history.back(); else window.location.href = backToURL;
}

function hideStatus() {
	window.defaultStatus='';
	window.status='';
	return true;
}

function easyFriendlyUrl(strToReplace, target, validchars, separator) {
    var str = "";
    var i;
    var str_accent = LETTERS_CHARS_ACCENT;
    var str_no_accent = LETTERS_CHARS_NO_ACCENT;
    var str_new = "";

    for (i = 0; i < strToReplace.length; i++) {
        if (str_accent.indexOf(strToReplace.charAt(i)) != -1) {
            str_new += str_no_accent.substr(str_accent.search(strToReplace.substr(i,1)),1);
        } else {
            str_new += strToReplace.substr(i,1);
        }
    }

    var exp_reg = new RegExp("[" + validchars + separator + "]");
    var exp_reg_space = new RegExp("[ ]");
    name2friendlyurl = str_new;
    name2friendlyurl.toString();
    name2friendlyurl = name2friendlyurl.replace(/^ +/, "");

    for (i = 0 ; i < name2friendlyurl.length; i++) {
        if (exp_reg.test(name2friendlyurl.charAt(i))) {
            str = str+name2friendlyurl.charAt(i);
        } else {
            if (exp_reg_space.test(name2friendlyurl.charAt(i))) {
                if (str.charAt(str.length-1) != separator) {
                    str = str + separator;
                }
            }
        }
    }

    if (str.charAt(str.length-1) == separator) str = str.substr(0, str.length-1);
    if (document.getElementById(target))
    document.getElementById(target).value = str.toLowerCase();
}

function $(id) {
	return document.getElementById(id);
}

function showText(text) {
	return unescape(text);
}

function displayMap() {
	var index, totalamount=10;
	var classname;
	if ($.cookie('showMap') == 1) {
		$('#resultsMap').css('display', '');
		$('#linkDisplayMap').text('' + showText(LANG_JS_LABEL_HIDEMAP) + '');
		for (index=1; index<=totalamount; index++) {
			if (document.getElementById('summaryNumberID'+index)) {
				classname=document.getElementById('summaryNumberID'+index).className;
				if (classname=='summaryNumberSC isHidden')
					document.getElementById('summaryNumberID'+index).className = 'summaryNumberSC show-inline';
				else
					document.getElementById('summaryNumberID'+index).className = 'summaryNumber show-inline';

			}
		}
		$.cookie('showMap', '0', {expires: 7, path: '/'});
		initialize();
	} else {
		$('#resultsMap').css('display', 'none');
		$('#linkDisplayMap').text('' + showText(LANG_JS_LABEL_SHOWMAP) + '');
		for (index=1; index<=totalamount; index++) {
			if (document.getElementById('summaryNumberID'+index)) {
				classname=document.getElementById('summaryNumberID'+index).className;
				if (classname=='summaryNumber isVisible')
					document.getElementById('summaryNumberID'+index).className = 'summaryNumber hide';
				else
					document.getElementById('summaryNumberID'+index).className = 'summaryNumberSC hide';
			}
		}
		$.cookie('showMap', '1', {expires: 7, path: '/'});
	}
}

function displayGraphics(path) {
	if ($.cookie('showGraphics') == 1) {
		$('#chart_1').slideUp('slow');
		$('#chart_2').slideUp('slow');
		$('#chart_3').slideUp('slow');
		$('#chart_4').slideUp('slow');
		$('#chart_5').slideUp('slow');

		$('#linkDisplayGraphics').text('' + showText(LANG_JS_LABEL_SHOWGRAPHICS) + '');
		$.cookie('showGraphics', '0', {expires: 7, path: path+'/'+SITEMGR_ALIAS+''});
		google.load("visualization", "1", {packages:["corechart"]});
		google.setOnLoadCallback(drawChart5);
	} else {
		$('#chart_1').slideDown('slow');
		$('#chart_2').slideDown('slow');
		$('#chart_3').slideDown('slow');
		$('#chart_4').slideDown('slow');
		$('#chart_5').slideDown('slow');

		$('#linkDisplayGraphics').text('' + showText(LANG_JS_LABEL_HIDEGRAPHICS) + '');
		$.cookie('showGraphics', '1', {expires: 7, path: path+'/'+SITEMGR_ALIAS+''});
		google.load("visualization", "1", {packages:["corechart"]});
		google.setOnLoadCallback(drawChart5);
	}
}

function dialogBox(pType, pText, pId, pForm ,pHeight, ok_button, cancel_button, urlRedir, lastLevel, lastTemplateId) {

	$('document').ready(function() {

		var btns = {};
		var type = pType;
		var text = pText;
		var id = pId;
		var formName = pForm;
		var boxHeight = pHeight;

		var button1 = ok_button;
		var button2 = cancel_button;

		var auxBgi = false;

		if ($.browser.msie && $.browser.version < 7) {
			auxBgi = true;
		}


		$('#alertMsg').remove();
		$('body').append(" <div id=\"alertMsg\" style=\"display:none\" ></div> ");
		$('#alertMsg').append(" <p class=\"informationMessage\">"+text+"</p> ");

		if ( type == 'confirm' || type == 'confirm_theme' || type == 'confirm_redirect') {

			/**
			 * Prepare labels to buttons of dialog box
			 */

            if (type == 'confirm_redirect') {

                btns[button1] = function(){
								document.location.href = urlRedir;
							};

                btns[button2] = function(){
                                    $(this).dialog('close');
                                    $("#listingtemplate_id").val(lastTemplateId);
                                    $("#level").val(lastLevel);
                                };

            } else {

                btns[button1] = function(){
                                    if ( formName ) {
                                        $("input[name='hiddenValue']").attr('value', id);
                                        document.getElementById(formName).submit();
                                    }
                                };

                if (type == 'confirm_theme') {

                    btns[button2] = function(){
                                        if ( formName ) {
                                            $("input[name='hiddenValue']").attr('value', 'no');
                                            document.getElementById(formName).submit();
                                        }
                                    };

                } else {

                    btns[button2] = function(){
                                        $(this).dialog('close');
                                        $('input:checkbox').attr('checked', '');
                                    };
                }
            }
			/****************************************/

			$("form").bind("submit", function(event) {event.preventDefault();});
		   	$('#alertMsg').dialog({
				autoOpen: false,
				bgiframe: auxBgi,
				closeOnEscape: false,
				resizable: false,
				height: boxHeight,
				draggable: false,
				modal: true,
				overlay: {
					backgroundColor: '#000',
					opacity: 0.5
				},
				buttons: btns
			});
			$('.ui-dialog-titlebar-close').css('display', 'none');
			$('#alertMsg').dialog('open');

		}

		else if ( type == 'alert' ) {

			$('#alertMsg').dialog({
				autoOpen: false,
				bgiframe: true,
				closeOnEscape: true,
				resizable: false,
				height: boxHeight,
				modal: true,
				overlay: {
					backgroundColor: '#000',
					opacity: 0.5
				}
			});
			$('#alertMsg').dialog('open');

		}

	});

}

function dialogBoxBulk(pType, pText, pId, pForm ,pHeight, ok_button, cancel_button) {

	$('document').ready(function() {

		var btns = {};
		var type = pType;
		var text = pText;
		var id = pId;
		var formName = pForm;
		var boxHeight = pHeight;

		var button1 = ok_button;
		var button2 = cancel_button;

		var auxBgi = false;

		if ($.browser.msie && $.browser.version < 7) {
			auxBgi = true;
		}


		$('#alertMsg').remove();
		$('body').append(" <div id=\"alertMsg\" style=\"display:none\" ></div> ");
		$('#alertMsg').append(" <p class=\"informationMessage\">"+text+"</p> ");

		if ( type == 'confirm' ) {


			/**
			 * Prepare labels to buttons of dialog box
			 */
			btns[button1] = function(){
								if ( formName ) {
									$("input[name='hiddenValue']").attr('value', id);
									document.getElementById(formName).submit();
								}
							};
			btns[button2] = function(){
								$(this).dialog('close');
								document.getElementById("bulkSubmit").value='';
							};
			/****************************************/

			$("form").bind("submit", function(event) {event.preventDefault();});
		   	$('#alertMsg').dialog({
				autoOpen: false,
				bgiframe: auxBgi,
				closeOnEscape: false,
				resizable: false,
				height: boxHeight,
				draggable: false,
				modal: true,
				overlay: {
					backgroundColor: '#000',
					opacity: 0.5
				},
				buttons: btns
			});
			$('.ui-dialog-titlebar-close').css('display', 'none');
			$('#alertMsg').dialog('open');

		}
		else if ( type == 'alert' ) {

			$('#alertMsg').dialog({
				autoOpen: false,
				bgiframe: true,
				closeOnEscape: true,
				resizable: false,
				height: boxHeight,
				modal: true,
				overlay: {
					backgroundColor: '#000',
					opacity: 0.5
				}
			});
			$('#alertMsg').dialog('open');

		}

	});

}

function JS_removeCategory(feed, order) {
    if (feed.selectedIndex >= 0) {
        $('#categoryAdd'+feed.options[feed.selectedIndex].value).after($('.categorySuccessMessage').empty());
        $('#categoryAdd'+feed.options[feed.selectedIndex].value ).fadeIn(500);
        feed.remove(feed.selectedIndex);
        if (order){
            orderCalculate();
        }
    }

	if(feed.length == 0){
    	$('#removeCategoriesButton').hide();
    }

}

function livemodeMessage(msg){
    $("#submit_button").attr("value", 0);
	fancy_alert(msg, "informationMessage", false, 450, 125, false);
}

function fancy_alert(msg, msgStyle, auto, width, height, modal) {
    if (typeof THEME_FLAT_FANCYBOX == "undefined") {
        $.fancybox(
            "<p class=\""+msgStyle+"\">"+msg+"</p>",
                {
                    'modal'             : modal,
                    'autoDimensions'	: auto,
                    'width'         	: width,
                    'height'         	: height,
                    'titleShow'         : false
                }
        );
    } else {
        if (THEME_FLAT_FANCYBOX && THEME_FLAT_FANCYBOX != "undefined") {
            $.fancybox(
                "<div class=\"modal-content-mini color-4\"><h2><span><a href=\"javascript:void(0);\" onclick=\"parent.$.fancybox.close();\">"+LANG_JS_CLOSE+"</a></span></h2><p class=\""+msgStyle+"\">"+msg+"</p></div>",
                    {
                        'maxWidth'          : 475,
                        'padding'           : 0,
                        'margin'            : 0,
                        'closeBtn'          : false,
                        'titleShow'         : false,
                        'showCloseButton'   : false
                    }
            );
        } else {
            $.fancybox(
                "<p class=\""+msgStyle+"\">"+msg+"</p>",
                    {
                        'modal'             : modal,
                        'autoDimensions'	: auto,
                        'width'         	: width,
                        'height'         	: height,
                        'titleShow'         : false
                    }
            );
        }
    }
}

function itemInQuicklist (action, user, item, type) {
	if (user && item && type) {

        $.post(DEFAULT_URL + "/quicklist.php", {
			type_action: 'check',
            from: 'Quicklist',
			action: action,
			account_id: user,
			item_id: item,
			item_type: type
		}, function (ret) {
			if (ret == "ok" || action != "add") {
                $.post(DEFAULT_URL + "/quicklist.php", {
                    type_action: 'save',
                    from: 'Quicklist',
                    action: action,
                    account_id: user,
                    item_id: item,
                    item_type: type
                }, function () {
                    if (action == "add") {
                        fancy_alert(showText(LANG_JS_FAVORITEADD), "informationMessage", false, 350, 'auto', false);
                    } else {
                        fancy_alert(showText(LANG_JS_FAVORITEDEL), "informationMessage", false, 350, 'auto', false);
                        setTimeout("window.location.reload()", 2000);
                    }
                });
			} else {
                fancy_alert(showText(LANG_JS_FAVORITES_ADDED), "errorMessage", false, 350, 'auto', false);
			}
		});


	}
}

function changePageScreen(url, id , page, params) {
	var url_destiny = "";
	if (params){
		url_destiny = url+params+"/screen/"+page;
	}else{
		url_destiny = url+"/screen/"+page;
	}
	window.location = url_destiny;
}

function changePageOrder(url, option, params) {
	var url_destiny = "";
	if (params){
		if (option)
			url_destiny = url+params+"/orderby/"+option;
		else
			url_destiny = url+params;
	}else{
		if (option)
			url_destiny = url+"/orderby/"+option;
		else
			url_destiny = url+params;
	}
	window.location = url_destiny;
}

/*
 * Copy username field value to email field
 */
function populateField(field_value, field_id){
	document.getElementById(field_id).value = field_value;
}

function in_array (x, matriz) {
	var txt = "�" + matriz.join("�") + "�";
	var er = new RegExp ("�" + x + "�", "gim");
	return ( (txt.match (er)) ? true : false );
}

function Redirect(option){
	if (option){
		location.href=option;
	}
}

function scrollPage(position_id){
	if(!position_id){
		$position_id = '#resultsMap';
	}else {
		$position_id = position_id;
	}
	$('html,body').animate({scrollTop: $($position_id).offset().top},'slow');
}

function urlencode( str ) {

    var histogram = {}, tmp_arr = [];
    var ret = (str+'').toString();

    var replacer = function(searchT, replace, str) {
        var tmp_arr = [];
        tmp_arr = str.split(searchT);
        return tmp_arr.join(replace);
    };

    // The histogram is identical to the one in urldecode.
    histogram["'"]   = '%27';
    histogram['(']   = '%28';
    histogram[')']   = '%29';
    histogram['*']   = '%2A';
    histogram['~']   = '%7E';
    histogram['!']   = '%21';
    histogram['%20'] = '+';
    histogram['\u20AC'] = '%80';
    histogram['\u0081'] = '%81';
    histogram['\u201A'] = '%82';
    histogram['\u0192'] = '%83';
    histogram['\u201E'] = '%84';
    histogram['\u2026'] = '%85';
    histogram['\u2020'] = '%86';
    histogram['\u2021'] = '%87';
    histogram['\u02C6'] = '%88';
    histogram['\u2030'] = '%89';
    histogram['\u0160'] = '%8A';
    histogram['\u2039'] = '%8B';
    histogram['\u0152'] = '%8C';
    histogram['\u008D'] = '%8D';
    histogram['\u017D'] = '%8E';
    histogram['\u008F'] = '%8F';
    histogram['\u0090'] = '%90';
    histogram['\u2018'] = '%91';
    histogram['\u2019'] = '%92';
    histogram['\u201C'] = '%93';
    histogram['\u201D'] = '%94';
    histogram['\u2022'] = '%95';
    histogram['\u2013'] = '%96';
    histogram['\u2014'] = '%97';
    histogram['\u02DC'] = '%98';
    histogram['\u2122'] = '%99';
    histogram['\u0161'] = '%9A';
    histogram['\u203A'] = '%9B';
    histogram['\u0153'] = '%9C';
    histogram['\u009D'] = '%9D';
    histogram['\u017E'] = '%9E';
    histogram['\u0178'] = '%9F';

    // Begin with encodeURIComponent, which most resembles PHP's encoding functions
    ret = encodeURIComponent(ret);

    for (searchT in histogram) {
        replace = histogram[searchT];
        ret = replacer(searchT, replace, ret) // Custom replace. No regexing
    }

    // Uppercase for full PHP compatibility
    return ret.replace(/(\%([a-z0-9]{2}))/g, function(full, m1, m2) {
        return "%"+m2.toUpperCase();
    });

    return ret;
}

function showCategory(item_id, item_type, user, account_id, featured, summary){
	$.get(DEFAULT_URL + "/includes/code/showcategory.php", {
		item_id: item_id,
		item_type: item_type,
		user: user,
		account_id: account_id,
		summary: summary
	}, function (ret) {
        if (featured){
            $("#showCategory_"+item_type+item_id).html(ret);
        } else {
            $("#showCategory_"+item_id).html(ret);
        }
	});
}

/**
 * Finds position of first occurrence of a string within another
 */
function eDirectory_strpos (haystack, needle, offset) {
    var i = (haystack + '').indexOf(needle, (offset || 0));
    return i === -1 ? false : i;
}

function controlTopnavbar(){
    if (topNavbarOptions){
        $('#topNavbar-options').slideUp('slow');
        topNavbarOptions = false;
    } else {
        $('#topNavbar-options').slideDown('slow');
        topNavbarOptions = true;
    }
}

function displayMediaFront(type){
    if (type == "video"){
        $("#detail_image").hide();
        $("#detail_gallery").hide();
        $("#detail_video").fadeIn();
        $("#li_gallery").removeClass("tab_media_active");
        $("#li_video").addClass("tab_media_active");
    }else if (type == "gallery"){
        $("#detail_gallery").fadeIn();
        $("#detail_image").fadeIn();
        $("#detail_video").hide();
        $("#li_gallery").addClass("tab_media_active");
        $("#li_video").removeClass("tab_media_active");
    }
}

function subscribeNewsletter() {
    var name = $("#newsname").val();
    var email = $("#newsemail").val();

    $.post(DEFAULT_URL + "/arcamailer_signup.php", {
        name:       name,
        email:      email,
        type:       'visitor'
    }, function (response) {
        var responseStr = response.split('||');
        if (responseStr[0] == "ok") {
            $("#newsname").attr("value", "");
            $("#newsemail").attr("value", "");
            if ($().placeholder) {
                $('input').placeholder();
            }
            $("#news_returnMessage").html("<p class=\"successMessage\">"+responseStr[1]+"</p>");
        } else {
            $("#news_returnMessage").html("<p class=\"errorMessage\">"+responseStr[1]+"</p>");
        }
        $("#news_returnMessage").fadeIn();
    });
}

function contactSiderbar(part1, part2) {
    if ($("#contactSidebarInfo").length) {
        $("#contactSidebarInfo").html("<i class=\"icon-envelope\"></i> "+part1+"@"+part2);
    } else {
        $("#contactSidebarInfo_noicon").html(part1+"@"+part2);
    }
    $("#contactSidebar").fadeIn();
}

function rateReview(review_id, action, div_name) {

    var div_name2 = "";

    if (!div_name) {
        div_name = "ratings_";
        div_name2 = "ratingsAjax_";
    } else {
        if (div_name == "ratings_") {
            div_name2 = "ratingsAjax_";
        } else {
            div_name2 = "ratings_";
        }
    }

    $.post(DEFAULT_URL + "/rateReview.php", {
        id:         review_id,
        action:     action,
        div_name:   div_name
    }, function (response) {
        if (response != "invalid ID") {
            $("#"+div_name+review_id).html(response);
            $("#"+div_name2+review_id).html(response);
        }
    });
}

function showTabDetail (type, scroll) {
    var activeTab = "#tab_" + type;
    var activeTabContent = "#content_" + type;

    if (scroll) {
        $('html, body').animate({
            scrollTop: $('#tab_review').offset().top
        }, 500);
    }

    $("ul.tabs li").removeClass("active"); //Remove any "active" class
    $(activeTab).addClass("active"); //Add "active" class to selected tab
    $(".tab-content").hide(); //Hide all tab content
    $(activeTabContent).fadeIn(); //Fade in the active content
}

function updateDeals(valueDone, valueLeft){
    $("#updateDeals").text(valueDone);
    $("#updateDealsLeft").text(valueLeft);
}

function loadReviews(item_type, item_id, screen, source) {
    if ((source == "tab" && !reviewsLoaded) || source != "tab") {
        $("#loading_reviews").css("display", "");
        $("#all_reviews").html("");
        $.post(DEFAULT_URL + "/loadReviews.php", {
            item_type:  item_type,
            item_id:    item_id,
            screen:     screen
        }, function (response) {
            $("#loading_reviews").css("display", "none");
            $(".loading_reviews_preview").css("display", "none");
            $("#all_reviews").html(response);
            $(".all_reviews_preview").html(response);
        });

        reviewsLoaded = true;
    }
}

function closeNewsletter() {
    $("#boxNewsletter").hide();
    $.cookie('hideNewsletter', '1', {expires: 7, path: '/'});
}

function escapeHTML(html) {
    var escape = document.createElement('textarea');
    escape.innerHTML = html;
    return escape.innerHTML;
}

function unescapeHTML(html) {
    var escape = document.createElement('textarea');
    escape.innerHTML = html;
    return escape.value;
}

function showMapResults(module) {

    $("ul.tabs li").removeClass("active"); //Remove all "active" class
    $("#tab_mapView").addClass("active"); //Add "active" class to selected tab

    $("#content_listView").hide(); //Hide list view content
    $("#resultsInfo_list").hide(); //Hide list view content

    $("#content_mapView").fadeIn(); //Fade in the active content
    $("#resultsInfo_map").show(); //Fade in the active content

    var getInfo = [];
    $('#resultsVars input:hidden').each(function(index) {
        getInfo[index] = [$(this).attr('name'), $(this).attr('value')];
    });

    var parameters = {
        "arrayGet[]": getInfo,
        "module": module
    };

    $("#control_openMap").attr("value", "true");

    $.post(DEFAULT_URL + "/loadmap.php", parameters, function (ret) {
        $("#content_mapView").append("<script type='text/javascript'>" + ret + "</" + "script>");
        initialize();
        $("#total_results").html(totalPoints);
        $("#search-info-results").css("display", "");
    });

}

function collapseBrowseBy(obj, id, type, changeClass, children) {
    var auxObj;

    if (changeClass) {

        if (children) {
            auxObj = obj.find("a");
        } else {
            auxObj = obj;
        }

        collapseChangeClass(auxObj);
    }

    if ($("#"+type+"_"+id).css("display") == "none") {
        $("#"+type+"_"+id).slideDown("slow");
    } else {
        $("#"+type+"_"+id).slideUp("slow");
    }

}

function collapseChangeClass(obj) {

    if ((obj).hasClass("icon-caret-right")) {
        obj.removeClass("icon-caret-right");
        obj.addClass("icon-caret-down");
    } else {
        obj.removeClass("icon-caret-down");
        obj.addClass("icon-caret-right");
    }

}

function collapseMenu(item) {
    if (item == "search") {

        if ($('#nav-collapse').hasClass('in')) {
            $('#nav-collapse').collapse('hide');
        }

    } else if (item == "menu") {

        if ($('#search-collapse').hasClass('in')) {
            $('#search-collapse').collapse('hide');
        }
    }
}

function getEventsCalendar(day, month, year) {

    if ($("#last_day_click").val() != day+month+year) {

        $("#last_day_click").attr("value", day+month+year);

        $(".calendar_loading").css("display", "");
        $("#calendar_event").html("");

        $(".calendar-event li").each(function() {
            $(this).removeClass("active");
        });

        $("#"+day+month+year).addClass("active");

        if (month < 10) {
            month = "0" + month;
        }

        if (day < 10) {
            day = "0" + day;
        }

        var parameters = {
            "searchBy":     "calendarList",
            "day":          day,
            "month":        month,
            "year":         year,
            "front_cal" :   '1'
        };

        $.get(DEFAULT_URL + "/getEvent.php", parameters, function(ret) {
            $(".calendar_loading").css("display", "none");
            $("#calendar_event").html(ret);
        });

    }

}

function loadToolTip(area) {
    if (area == "general") {

        $('#priceTip, .symbol a, .summary-price a, .list-search label, .button-call img, .button-send img, .share-social img, .summary-icons span a.map-link, #tip_rss').tooltip({
            animation: true
        });

        $('#tab_mapView').tooltip({
            animation: true,
            placement: 'bottom'
        })

    } else if (area == "detail") {

        $('#priceTip, .list-search label, .button-call img, .button-send img, .share-social img').tooltip({
            animation: true
        });

    } else if (area == "summary_ajax") {

        $('.summary-price a, .button-call img, .button-send img, .share-social img').tooltip({
            animation: true
        });

    }
}

function showAdvancedSearch(item_type, template_id, load_cat, category_id) {

	var aux_data = "category_id="+category_id+"&fnct=categories&type="+item_type;

	if (load_cat){
		/*
		 * Load dropdown using ajax
		 */
		if (template_id > 0) {
			aux_data += "&template_id="+template_id;
		}

		$.ajax({
		  url: DEFAULT_URL+"/advancedsearch_categories.php",
		  context: document.body,
		  data: aux_data,
		  success: function(html){
			$("#advanced_search_category_dropdown").html(html);
            if ($().selectpicker) {
                $('.selectpicker .select').selectpicker();
            }
		  }
		});
	}

	if (document.getElementById("locations_default_where")) {
		if (document.getElementById("locations_default_where").value) {
			if (document.getElementById("locations_default_where_replace").value == "yes") {
                $("#where, #where_resp").attr("value", $("#locations_default_where").val());
            }
        }
	}

    document.getElementById("advanced-search-button").onclick = function() {
		closeAdvancedSearch(item_type, template_id, category_id);
	}

	$('#advanced-search').slideDown('slow');
	$('#advanced-search-label').hide();
	$('#advanced-search-label-close').show();
}

function closeAdvancedSearch(item_type, template_id, category_id) {

    $('#advanced-search').slideUp('slow', function() {
        document.getElementById("advanced-search-button").onclick = function() {
            showAdvancedSearch(item_type, template_id, false, category_id);
        }
    });
	$('#advanced-search-label').show();
	$('#advanced-search-label-close').hide();

}

function clearAdvancedOptions() {
    $('#divAdvSearchFields input:radio').each(function() {
       this.checked = false;
    });
    $('.list-home li').each(function() {
       $(this).find("label").removeClass("active");
    });
}

function checkRadio(obj) {
    $('.list-home li').each(function() {
       $(this).find("label").removeClass("active");
    });
    obj.find("label").addClass("active");
}

function get_nth_suffix(date) {
   switch (date) {
     case 1:
     case 21:
     case 31:
        return 'st';
     case 2:
     case 22:
        return 'nd';
     case 3:
     case 23:
        return 'rd';
     default:
        return 'th';
   }
 }

function myDateFunction(id) {
	var date = $("#" + id).data("date");
	window.location.href="eventList?date=" + moment(date, "YYYY-MM-DD").format("YYYYMMDD");
}

function myNavFunction(id) {
  var nav = $("#" + id).data("navigation");
  var to = $("#" + id).data("to");
}