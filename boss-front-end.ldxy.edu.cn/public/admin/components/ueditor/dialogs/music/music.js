!function() {
    var uploadFile, onlineFile;
    function initTabs() {
        for (var e = $G("tabhead").children, t = 0; t < e.length; t++)
            domUtils.on(e[t], "click", function(e) {
                setTabFocus((e.target || e.srcElement).getAttribute("data-content-id"))
            });
        setTabFocus("upload")
    }
    function setTabFocus(e) {
        if (e) {
            var t, i, a = $G("tabhead").children;
            for (t = 0; t < a.length; t++)
                (i = a[t].getAttribute("data-content-id")) == e ? (domUtils.addClass(a[t], "focus"),
                domUtils.addClass($G(i), "focus")) : (domUtils.removeClasses(a[t], "focus"),
                domUtils.removeClasses($G(i), "focus"));
            switch (e) {
            case "upload":
                uploadFile = uploadFile || new UploadFile("queueList");
                break;
            case "online":
                onlineFile = onlineFile || new OnlineFile("fileList")
            }
        }
    }
    function initButtons() {
        dialog.onok = function() {
            for (var e, t = [], i = $G("tabhead").children, a = 0; a < i.length; a++)
                if (domUtils.hasClass(i[a], "focus")) {
                    e = i[a].getAttribute("data-content-id");
                    break
                }
            switch (e) {
            case "upload":
                t = uploadFile.getInsertList();
                var s = uploadFile.getQueueCount();
                if (s)
                    return $(".info", "#queueList").html('<span style="color:red;">' + "还有2个未上传文件".replace(/[\d]/, s) + "</span>"),
                    !1;
                break;
            case "online":
                t = onlineFile.getInsertList()
            }
            editor.execCommand("music", t)
        }
    }
    function UploadFile(e) {
        this.$wrap = e.constructor == String ? $("#" + e) : $(e),
        this.init()
    }
    function OnlineFile(e) {
        this.container = utils.isString(e) ? document.getElementById(e) : e,
        this.init()
    }
    window.onload = function() {
        initTabs(),
        initButtons()
    }
    ,
    UploadFile.prototype = {
        init: function() {
            this.fileList = [],
            this.initContainer(),
            this.initUploader()
        },
        initContainer: function() {
            this.$queue = this.$wrap.find(".filelist")
        },
        initUploader: function() {
            var d, e, t, n = this, u = jQuery, i = n.$wrap, a = i.find(".filelist"), s = i.find(".statusBar"), l = s.find(".info"), o = i.find(".uploadBtn"), p = (i.find(".filePickerBtn"),
            i.find(".filePickerBlock")), r = i.find(".placeholder"), c = s.find(".progress").hide(), f = 0, h = 0, g = window.devicePixelRatio || 1, m = 113 * g, v = 113 * g, C = "", b = {}, x = (e = document.createElement("p").style,
            t = "transition"in e || "WebkitTransition"in e || "MozTransition"in e || "msTransition"in e || "OTransition"in e,
            e = null,
            t), w = "/api/upload/audio", F = editor.getOpt("fileMaxSize"), U = (editor.getOpt("fileAllowFiles") || []).join("").replace(/\./g, ",").replace(/^[,]/, "");
            function S(i) {
                function a(e) {
                    switch (e) {
                    case "exceed_size":
                        text = lang.errorExceedSize;
                        break;
                    case "interrupt":
                        text = lang.errorInterrupt;
                        break;
                    case "http":
                        text = lang.errorHttp;
                        break;
                    case "not_allow_type":
                        text = lang.errorFileType;
                        break;
                    default:
                        text = lang.errorUploadRetry
                    }
                    r.text(text).show()
                }
                var s = u('<li id="' + i.id + '"><p class="title">' + i.name + '</p><p class="imgWrap"></p><p class="progress"><span></span></p></li>')
                  , n = u('<div class="file-panel"><span class="cancel">' + lang.uploadDelete + '</span><span class="rotateRight">' + lang.uploadTurnRight + '</span><span class="rotateLeft">' + lang.uploadTurnLeft + "</span></div>").appendTo(s)
                  , l = s.find("p.progress span")
                  , o = s.find("p.imgWrap")
                  , r = u('<p class="error"></p>').hide().appendTo(s);
                "invalid" === i.getStatus() ? a(i.statusText) : (o.text(lang.uploadPreview),
                -1 == "|png|jpg|jpeg|bmp|gif|".indexOf("|" + i.ext.toLowerCase() + "|") ? o.empty().addClass("notimage").append('<i class="file-preview file-type-' + i.ext.toLowerCase() + '"></i><span class="file-title" title="' + i.name + '">' + i.name + "</span>") : browser.ie && browser.version <= 7 ? o.text(lang.uploadNoPreview) : d.makeThumb(i, function(e, t) {
                    if (e || !t)
                        o.text(lang.uploadNoPreview);
                    else {
                        var i = u('<img src="' + t + '">');
                        o.empty().append(i),
                        i.on("error", function() {
                            o.text(lang.uploadNoPreview)
                        })
                    }
                }, m, v),
                b[i.id] = [i.size, 0],
                i.rotation = 0,
                i.ext && -1 != U.indexOf(i.ext.toLowerCase()) || (a("not_allow_type"),
                d.removeFile(i))),
                i.on("statuschange", function(e, t) {
                    "progress" === t ? l.hide().width(0) : "queued" === t && (s.off("mouseenter mouseleave"),
                    n.remove()),
                    "error" === e || "invalid" === e ? (a(i.statusText),
                    b[i.id][1] = 1) : "interrupt" === e ? a("interrupt") : "queued" === e ? b[i.id][1] = 0 : "progress" === e && (r.hide(),
                    l.css("display", "block")),
                    s.removeClass("state-" + t).addClass("state-" + e)
                }),
                s.on("mouseenter", function() {
                    n.stop().animate({
                        height: 30
                    })
                }),
                s.on("mouseleave", function() {
                    n.stop().animate({
                        height: 0
                    })
                }),
                n.on("click", "span", function() {
                    var e;
                    switch (u(this).index()) {
                    case 0:
                        return void d.removeFile(i);
                    case 1:
                        i.rotation += 90;
                        break;
                    case 2:
                        i.rotation -= 90
                    }
                    x ? (e = "rotate(" + i.rotation + "deg)",
                    o.css({
                        "-webkit-transform": e,
                        "-mos-transform": e,
                        "-o-transform": e,
                        transform: e
                    })) : o.css("filter", "progid:DXImageTransform.Microsoft.BasicImage(rotation=" + ~~(i.rotation / 90 % 4 + 4) % 4 + ")")
                }),
                s.insertBefore(p)
            }
            function y() {
                var e, i = 0, a = 0, t = c.children();
                u.each(b, function(e, t) {
                    a += t[0],
                    i += t[0] * t[1]
                }),
                e = a ? i / a : 0,
                t.eq(0).text(Math.round(100 * e) + "%"),
                t.eq(1).css("width", Math.round(100 * e) + "%"),
                _()
            }
            function k(e) {
                if (e != C) {
                    var t = d.getStats();
                    switch (o.removeClass("state-" + C),
                    o.addClass("state-" + e),
                    e) {
                    case "pedding":
                        a.addClass("element-invisible"),
                        s.addClass("element-invisible"),
                        r.removeClass("element-invisible"),
                        c.hide(),
                        l.hide(),
                        d.refresh();
                        break;
                    case "ready":
                        r.addClass("element-invisible"),
                        a.removeClass("element-invisible"),
                        s.removeClass("element-invisible"),
                        c.hide(),
                        l.show(),
                        o.text(lang.uploadStart),
                        d.refresh();
                        break;
                    case "uploading":
                        c.show(),
                        l.hide(),
                        o.text(lang.uploadPause);
                        break;
                    case "paused":
                        c.show(),
                        l.hide(),
                        o.text(lang.uploadContinue);
                        break;
                    case "confirm":
                        if (c.show(),
                        l.hide(),
                        o.text(lang.uploadStart),
                        (t = d.getStats()).successNum && !t.uploadFailNum)
                            return void k("finish");
                        break;
                    case "finish":
                        c.hide(),
                        l.show(),
                        t.uploadFailNum ? o.text(lang.uploadRetry) : o.text(lang.uploadStart)
                    }
                    C = e,
                    _()
                }
                n.getQueueCount() ? o.removeClass("disabled") : o.addClass("disabled")
            }
            function _() {
                var e, t = "";
                "ready" === C ? t = lang.updateStatusReady.replace("_", f).replace("_KB", WebUploader.formatSize(h)) : "confirm" === C ? (e = d.getStats()).uploadFailNum && (t = lang.updateStatusConfirm.replace("_", e.successNum).replace("_", e.successNum)) : (e = d.getStats(),
                t = lang.updateStatusFinish.replace("_", f).replace("_KB", WebUploader.formatSize(h)).replace("_", e.successNum),
                e.uploadFailNum && (t += lang.updateStatusError.replace("_", e.uploadFailNum))),
                l.html(t)
            }
            WebUploader.Uploader.support() ? editor.getOpt("fileActionName") ? ((d = n.uploader = WebUploader.create({
                pick: {
                    id: "#musicPickerReady",
                    label: lang.uploadSelectFile
                },
                accept: {
                    title: "Audio",
                    extensions: "mp3,ogg,wav",
                    mimeTypes: "audio/mpeg,audio/ogg,audio/wav"
                },
                swf: "../../third-party/webuploader/Uploader.swf",
                server: w,
                fileVal: editor.getOpt("fileFieldName"),
                duplicate: !0,
                fileSingleSizeLimit: F,
                compress: !1
            })).addButton({
                id: "#filePickerBlock"
            }),
            d.addButton({
                id: "#filePickerBtn",
                label: lang.uploadAddFile
            }),
            k("pedding"),
            d.on("fileQueued", function(e) {
                f++,
                h += e.size,
                1 === f && (r.addClass("element-invisible"),
                s.show()),
                S(e)
            }),
            d.on("fileDequeued", function(e) {
                var t, i;
                f--,
                h -= e.size,
                i = u("#" + (t = e).id),
                delete b[t.id],
                y(),
                i.off().find(".file-panel").off().end().remove(),
                y()
            }),
            d.on("filesQueued", function(e) {
                d.isInProgress() || "pedding" != C && "finish" != C && "confirm" != C && "ready" != C || k("ready"),
                y()
            }),
            d.on("all", function(e, t) {
                switch (e) {
                case "uploadFinished":
                    k("confirm");
                    break;
                case "startUpload":
                    var i = utils.serializeParam(editor.queryCommandValue("serverparam")) || ""
                      , a = utils.formatUrl(w + (-1 == w.indexOf("?") ? "?" : "&") + "encode=utf-8&" + i);
                    d.option("server", a),
                    k("uploading");
                    break;
                case "stopUpload":
                    k("paused")
                }
            }),
            d.on("uploadBeforeSend", function(e, t, i) {
                i.X_Requested_With = "XMLHttpRequest"
            }),
            d.on("uploadProgress", function(e, t) {
                u("#" + e.id).find(".progress span").css("width", 100 * t + "%"),
                b[e.id][1] = t,
                y()
            }),
            d.on("uploadSuccess", function(e, t) {
                var i = u("#" + e.id);
                try {
                    var a = t._raw || t
                      , s = utils.str2json(a);
                    "SUCCESS" == s.state ? (n.fileList.push(s),
                    i.append('<span class="success"></span>')) : i.find(".error").text(s.state).show()
                } catch (e) {
                    i.find(".error").text(lang.errorServerUpload).show()
                }
            }),
            d.on("uploadError", function(e, t) {}),
            d.on("error", function(e, t) {
                "Q_TYPE_DENIED" != e && "F_EXCEED_SIZE" != e || S(t)
            }),
            d.on("uploadComplete", function(e, t) {}),
            o.on("click", function() {
                if (u(this).hasClass("disabled"))
                    return !1;
                "ready" === C || "paused" === C ? d.upload() : "uploading" === C && d.stop()
            }),
            o.addClass("state-" + C),
            y()) : u("#musicPickerReady").after(u("<div>").html(lang.errorLoadConfig)).hide() : u("#musicPickerReady").after(u("<div>").html(lang.errorNotSupport)).hide()
        },
        getQueueCount: function() {
            var e, t, i, a = 0, s = this.uploader.getFiles();
            for (t = 0; e = s[t++]; )
                "queued" != (i = e.getStatus()) && "uploading" != i && "progress" != i || a++;
            return a
        },
        getInsertList: function() {
            var e, t, i, a = [], s = editor.getOpt("fileUrlPrefix");
            for (e = 0; e < this.fileList.length; e++)
                t = (i = this.fileList[e]).url,
                a.push({
                    title: i.original || t.substr(t.lastIndexOf("/") + 1),
                    url: s + t
                });
            return a
        }
    },
    OnlineFile.prototype = {
        init: function() {
            this.initContainer(),
            this.initEvents(),
            this.initData()
        },
        initContainer: function() {
            this.container.innerHTML = "",
            this.list = document.createElement("ul"),
            this.clearFloat = document.createElement("li"),
            domUtils.addClass(this.list, "list"),
            domUtils.addClass(this.clearFloat, "clearFloat"),
            this.list.appendChild(this.clearFloat),
            this.container.appendChild(this.list)
        },
        initEvents: function() {
            var t = this;
            domUtils.on($G("fileList"), "scroll", function(e) {
                this.scrollHeight - (this.offsetHeight + this.scrollTop) < 10 && t.getFileData()
            }),
            domUtils.on(this.list, "click", function(e) {
                var t = (e.target || e.srcElement).parentNode;
                "li" == t.tagName.toLowerCase() && (domUtils.hasClass(t, "selected") ? domUtils.removeClasses(t, "selected") : domUtils.addClass(t, "selected"))
            })
        },
        initData: function() {
            this.state = 0,
            this.listSize = editor.getOpt("fileManagerListSize"),
            this.listIndex = 0,
            this.listEnd = !1,
            this.getFileData()
        },
        getFileData: function() {
            var _this = this;
            _this.listEnd || this.isLoadingData || (this.isLoadingData = !0,
            ajax.request(editor.getActionUrl(editor.getOpt("fileManagerActionName")), {
                timeout: 1e5,
                data: utils.extend({
                    start: this.listIndex,
                    size: this.listSize
                }, editor.queryCommandValue("serverparam")),
                method: "get",
                onsuccess: function(r) {
                    try {
                        var json = eval("(" + r.responseText + ")");
                        "SUCCESS" == json.state && (_this.pushData(json.list),
                        _this.listIndex = parseInt(json.start) + parseInt(json.list.length),
                        _this.listIndex >= json.total && (_this.listEnd = !0),
                        _this.isLoadingData = !1)
                    } catch (e) {
                        if (-1 != r.responseText.indexOf("ue_separate_ue")) {
                            var list = r.responseText.split(r.responseText);
                            _this.pushData(list),
                            _this.listIndex = parseInt(list.length),
                            _this.listEnd = !0,
                            _this.isLoadingData = !1
                        }
                    }
                },
                onerror: function() {
                    _this.isLoadingData = !1
                }
            }))
        },
        pushData: function(e) {
            var t, i, a, s, n, l = this, o = editor.getOpt("fileManagerUrlPrefix");
            for (t = 0; t < e.length; t++)
                if (e[t] && e[t].url) {
                    if (i = document.createElement("li"),
                    n = document.createElement("span"),
                    a = e[t].url.substr(e[t].url.lastIndexOf(".") + 1),
                    -1 != "png|jpg|jpeg|gif|bmp".indexOf(a))
                        s = document.createElement("img"),
                        domUtils.on(s, "load", function(e) {
                            return function() {
                                l.scale(e, e.parentNode.offsetWidth, e.parentNode.offsetHeight)
                            }
                        }(s)),
                        s.width = 113,
                        s.setAttribute("src", o + e[t].url + (-1 == e[t].url.indexOf("?") ? "?noCache=" : "&noCache=") + (+new Date).toString(36));
                    else {
                        var r = document.createElement("i")
                          , d = document.createElement("span");
                        d.innerHTML = e[t].url.substr(e[t].url.lastIndexOf("/") + 1),
                        (s = document.createElement("div")).appendChild(r),
                        s.appendChild(d),
                        domUtils.addClass(s, "file-wrapper"),
                        domUtils.addClass(d, "file-title"),
                        domUtils.addClass(r, "file-type-" + a),
                        domUtils.addClass(r, "file-preview")
                    }
                    domUtils.addClass(n, "icon"),
                    i.setAttribute("data-url", o + e[t].url),
                    e[t].original && i.setAttribute("data-title", e[t].original),
                    i.appendChild(s),
                    i.appendChild(n),
                    this.list.insertBefore(i, this.clearFloat)
                }
        },
        scale: function(e, t, i, a) {
            var s = e.width
              , n = e.height;
            "justify" == a ? n <= s ? (e.width = t,
            e.height = i * n / s,
            e.style.marginLeft = "-" + parseInt((e.width - t) / 2) + "px") : (e.width = t * s / n,
            e.height = i,
            e.style.marginTop = "-" + parseInt((e.height - i) / 2) + "px") : n <= s ? (e.width = t * s / n,
            e.height = i,
            e.style.marginLeft = "-" + parseInt((e.width - t) / 2) + "px") : (e.width = t,
            e.height = i * n / s,
            e.style.marginTop = "-" + parseInt((e.height - i) / 2) + "px")
        },
        getInsertList: function() {
            var e, t = this.list.children, i = [];
            for (e = 0; e < t.length; e++)
                if (domUtils.hasClass(t[e], "selected")) {
                    var a = t[e].getAttribute("data-url")
                      , s = t[e].getAttribute("data-title") || a.substr(a.lastIndexOf("/") + 1);
                    i.push({
                        title: s,
                        url: a
                    })
                }
            return i
        }
    }
}();
