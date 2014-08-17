<?

	/*==================================================================*\
	######################################################################
	#                                                                    #
	# Copyright 2005 Arca Solutions, Inc. All Rights Reserved.           #
	#                                                                    #
	# This file may not be redistributed in whole or part.               #
	# eDirectory is licensed on a per-domain basis.                      #
	#                                                                    #
	# ---------------- eDirectory IS NOT FREE SOFTWARE ----------------- #
	#                                                                    #
	# http://www.edirectory.com | http://www.edirectory.com/license.html #
	######################################################################
	\*==================================================================*/

	# ----------------------------------------------------------------------------------------------------
	# * FILE: /theme/default/body/listing/results.php
	# ----------------------------------------------------------------------------------------------------
	
	/*
	 * Prepare content to results
	 */
    include(LISTING_EDIRECTORY_ROOT."/searchresults.php");
    $filter_item = LISTING_FEATURE_FOLDER;
    include(EDIRECTORY_ROOT."/search_filters.php");
    
    include(system_getFrontendPath("sitecontent_top.php")); ?>
    
    <div class="row-fluid span12">
       
        <? if (count($filters) > 0 && $show_results) { ?>
        
        <div class="span3">
            
            <div id="return_filter" class="sidebar-filters">
                <? include(system_getFrontendPath("filters.php")); ?>
            </div>
            
        </div>

        <? } ?>

        <div class="span9">

            <? if ($show_results) {
                
                if (count($filters) > 0 && !$listings) {
                    
                ?>
                    <div class="noresults">
                        
                        <div class="resultsMessage">
                            <?=system_showText(LANG_SEARCH_NORESULTS);?>
                        </div>
                        
                    </div>
                <?
                
                } else {
                    
                    if ($aux_module_items && !$hideResults) { ?>
            
                        <div class="visible-phone btn btn-inverse" onclick="CloseFilters();">
                            <?=system_showText(LANG_LABEL_FILTER)?>
                        </div>
            
                    <? } ?>

                    <div class="tabview-results custom-select-listing">

                        <div id="resultsInfo_list" <?=($openMap ? "style=\"display: none;\"" : "");?>>
                            <? include(system_getFrontendPath("results_filter.php"));?>
                        </div>

                        <div id="resultsInfo_map" <?=(!$openMap ? "style=\"display: none;\"" : "");?>>
                            <?
                            $addId = true;
                            include(system_getFrontendPath("results_info.php"));
                            $addId = false;
                            ?>
                        </div>

                    </div>

                    <? include(system_getFrontendPath("results_tabs.php")); ?>

                    <div id="content_listView" <?=($openMap ? "style=\"display: none;\"" : "");?>>

                        <div class="results-info-listing">
                            <? include(system_getFrontendPath("results_info.php")); ?>
                            <? include(system_getFrontendPath("results_pagination.php"));?>
                        </div>

                        <? include(LISTING_EDIRECTORY_ROOT."/results_listings.php"); ?>

                        <div class="bottom-pagination-listing">
                            <? 
                            $pagination_bottom = true;
                            $showLetter = false;
                            include(system_getFrontendPath("results_pagination.php"));
                            ?>
                        </div>

                    </div>
                    <? 
                    $ajaxMap = true;
                    include(system_getFrontendPath("results_maps.php")); 
                }
            } else {
                
                if ($search_lock) { ?>
            
                    <p class="errorMessage">
                        <?=system_showText(($search_lock_word ? str_replace("[FT_MIN_WORD_LEN]", FT_MIN_WORD_LEN, LANG_MSG_SEARCH_MIN_WORD_LEN) : LANG_MSG_LEASTONEPARAMETER))?>
                    </p>
                <? } else {
                    
                    $db = db_getDBObject();
                    if ($db->getRowCount("Listing_Summary") > 0) { ?>
                    
                        <div class="resultsMessage">
                            <?=system_showText(LANG_SEARCH_NORESULTS);?>
                        </div>
                    
                    <? } else { ?>
                    
                        <p class="informationMessage">
                            <?=system_showText(LANG_MSG_NOLISTINGS);?>
                        </p>
                        
                    <? }
                }
            }
            ?>
            
        </div>
        
        <? include(system_getFrontendPath("sitecontent_bottom.php")); ?>
    
	</div>