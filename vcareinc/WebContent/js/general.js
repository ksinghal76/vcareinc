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

function changeLinkTab(url, name, tabName) {
	var urlNm = "#" + tabName;
	$(urlNm).attr("href", url);
	$(urlNm).html(name);
}