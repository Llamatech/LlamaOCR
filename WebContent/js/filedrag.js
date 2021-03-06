/*
filedrag.js - HTML5 File Drag & Drop demonstration
Featured on SitePoint.com
Developed by Craig Buckler (@craigbuckler) of OptimalWorks.net
*/
(function() {

	// getElementById
	function $id(id) {
		return document.getElementById(id);
	}


	// output information
	function Output(msg) {
		document.getElementById("uploadFile").value = msg
	}


	// file drag hover
	function FileDragHover(e) {
		e.stopPropagation();
		e.preventDefault();
		e.target.className = (e.type == "dragover" ? "hover" : "");
	}


function FileDragHover2(e) {
		e.stopPropagation();
		e.preventDefault();
		//e.target.className = (e.type == "dragover" ? "hover" : "");
	}

	// file selection
	function FileSelectHandler(e) {

		// cancel event and hover styling
		FileDragHover(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f);
         UploadFile(f);
		}

	}

function FileSelectHandler2(e) {

		// cancel event and hover styling
		FileDragHover2(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f);
         UploadFile(f);
		}

	}


	// output file information
	function ParseFile(file) {

		Output(
			"File: " + file.name +
			" type: " + file.type +
			" size: " + file.size +
			"bytes"
		);

	}

   function UploadFile(file)
   {
      var formData = new FormData();
      //var fname = $id("fname").value;
      //var folder = $id("folder-sel").value;
      formData.append("file", file);
      //formData.append("fname", fname);
      //formData.append("carpeta-destino", folder);
      var xhr = new XMLHttpRequest();
      var req = $id("myForm").action;
      xhr.open("POST", ""+req, true);
		xhr.send(formData);
   }


	// initialize
	function Init() {

		var fileselect = $id("fileChooseBtn"),
			filedrag = $id("filedrag"),
			submitbutton = $id("submitBtn");

		// file select
		fileselect.addEventListener("change", FileSelectHandler2, false);

		// is XHR2 available?
		var xhr = new XMLHttpRequest();
		if (xhr.upload) {

			// file drop
			filedrag.addEventListener("dragover", FileDragHover, false);
			filedrag.addEventListener("dragleave", FileDragHover, false);
			filedrag.addEventListener("drop", FileSelectHandler, false);
			filedrag.style.display = "block";

			// remove submit button
//			submitbutton.style.display = "none";
         xhr.open("GET","ajax_info.txt",true);
         xhr.send();
		}

	}

	// call initialization file
	if (window.File && window.FileList && window.FileReader) {
		Init();
	}


})();



