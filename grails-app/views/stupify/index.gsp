<html>
<head>
	<title>Stupify.io</title>
	<script type="text/javascript" src="//api.filepicker.io/v1/filepicker.js"></script>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="${resource(dir: 'js', file: 'app.js')}"></script>
	<link href="${resource(dir: 'css', file: 'style.css')}" rel="stylesheet" type="text/css" />
	<link href='http://fonts.googleapis.com/css?family=Lato:100,400' rel='stylesheet' type='text/css'>
</head>
<body>
<div id="wrapper">
	<canvas id="canvas"></canvas>
	<div id="header"> <span id="headerTitle">Stupify<span style="font-size: .5em;">.io </span></style></div>
	<div id="inner">
		<div id="textareas">
			<div id ="leftSide">
				<span id="leftTitle">Enter Text to Stupify: </span>
				<textarea id="source" name="text" wrap="SOFT" tabindex="0" dir="ltr" spellcheck="false" autocapitalize="off" autocomplete="off" autocorrect="off" autofocus class="source-textarea" placeholder="The quick brown fox jumps over the lazy dog"></textarea>
				<span id="leftFooter">Type text or <span id="documentUpload">upload a document</span></span>

			</div>
			<div id = "rightSide">
				<span id="rightTitle">_</span>
				<textarea id="result" name="text" wrap="SOFT" tabindex="0" dir="ltr" spellcheck="false" autocapitalize="off" autocomplete="off" autocorrect="off" autofocus class="result-textarea" disabled></textarea>
				<span id="rightFooter"><span id="documentExport">Export</span> | <span id="viewMetrics">View Metrics </span></span>
			</div>
		</div>
		<div id="bottomButtonField">
			<button type="button" id="stupifyButton">Stupify</button>
		</div>
	</div>
</div>
</div>
</body>
<html>