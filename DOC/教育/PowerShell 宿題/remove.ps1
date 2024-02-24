
#PowerShell -ExecutionPolicy RemoteSigned .\remove.ps1
#Write-Host "True"
#画面ID始まる文字
$gamenFirst="G"

#ファイル名
$fileName=$null
#フォルダー名
$folderName=$null

$items = Get-ChildItem c:\work -File
foreach ($item in $items) {
	#クリア
	$fileName=$null
	$folderName=$null
	
	$fileName=$item.Name
	#Write-Host $fileName
	if ($fileName.IndexOf($gamenFirst) -gt 0) {
		$folderName=$fileName.substring($fileName.IndexOf($gamenFirst),5)
		#Write-Host $folderName
		#フォルダー作成
		if(-! (Test-Path $folderName)){
	  		New-Item $folderName -ItemType Directory
		}
		
		#ファイル移動
		#Write-Host $fileName 
		Move-Item $fileName $folderName
	}
}
