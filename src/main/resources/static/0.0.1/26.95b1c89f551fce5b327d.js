webpackJsonp([26],{1415:function(e,t,r){"use strict";function u(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var a=r(3),n=u(a),s=r(167),c=u(s),o=r(74),d=u(o),f=r(116),i=u(f),p=r(2922);t.default={namespace:"userDetail",state:{data:{}},subscriptions:{setup:function(e){var t=e.dispatch;e.history.listen(function(e){var r=e.pathname,u=(0,i.default)("/user/:id").exec(r);u&&t({type:"query",payload:{id:u[1]}})})}},effects:{query:c.default.mark(function e(t,r){var u,a,n,s,o,f=t.payload,i=r.call,l=r.put;return c.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,i(p.query,f);case 2:if(u=e.sent,a=u.success,n=u.message,s=u.status,o=(0,d.default)(u,["success","message","status"]),!a){e.next=9;break}return e.next=7,l({type:"querySuccess",payload:{data:o}});case 7:e.next=10;break;case 9:throw u;case 10:case"end":return e.stop()}},e,this)})},reducers:{querySuccess:function(e,t){var r=t.payload,u=r.data;return(0,n.default)({},e,{data:u})}}},e.exports=t.default},2922:function(e,t,r){"use strict";function u(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0}),t.update=t.remove=t.create=t.query=void 0;var a=r(167),n=u(a),s=r(332),c=u(s),o=(t.query=function(){var e=(0,c.default)(n.default.mark(function e(t){return n.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return console.log(f),e.abrupt("return",(0,o.request)({url:f,method:"get",data:t}));case 2:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),t.create=function(){var e=(0,c.default)(n.default.mark(function e(t){return n.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,o.request)({url:f.replace("/:id",""),method:"post",data:t}));case 1:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),t.remove=function(){var e=(0,c.default)(n.default.mark(function e(t){return n.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,o.request)({url:f,method:"delete",data:t}));case 1:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),t.update=function(){var e=(0,c.default)(n.default.mark(function e(t){return n.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return console.log(f),e.abrupt("return",(0,o.request)({url:f,method:"patch",data:t}));case 2:case"end":return e.stop()}},e,this)}));return function(t){return e.apply(this,arguments)}}(),r(83)),d=o.config.api,f=d.user}});