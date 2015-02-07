/* ��һ����ǩ */
function OpenTab(title, url, icon){
    /**
    ����������ı�ǩ���ڣ���ѡ��ñ�ǩ
    �������һ����ǩ����ǩ��
    */
    if($("#tabs").tabs('exists', title)){
        $("#tabs").tabs('select', title);
        
    
        
        
    }else{
        $("#tabs").tabs('add',{
            title: title,
            content: createTabContent(url),
            closable: true,
            icon: icon,
            tools:[{    
                iconCls:'icon-mini-refresh',    
                handler:function(){    
                 //   alert('refresh');  
                //	refreshList();
                	//刷新当前tab
//                    var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
//                    var url = $(currTab.panel('options').content).attr('src');
//                    self.parent.$('#tabs').tabs('update', {
//                      tab : currTab,
//                      options : {
//                       content : createFrame(url)
//                      }
//                     });
                	
                    $('.tabs-panels > .panel:visible > .panel-body > iframe').get(0).contentDocument.location.reload(true);
                
                }    
            }]
        });
    }    
}

/* ��ɱ�ǩ���� */
function createTabContent(url){
    return '<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="' + url + '"></iframe>';
}