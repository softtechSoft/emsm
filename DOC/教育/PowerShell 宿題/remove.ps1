
#PowerShell -ExecutionPolicy RemoteSigned .\remove.ps1
#Write-Host "True"
#���ID�n�܂镶��
$gamenFirst="G"

#�t�@�C����
$fileName=$null
#�t�H���_�[��
$folderName=$null

$items = Get-ChildItem c:\work -File
foreach ($item in $items) {
	#�N���A
	$fileName=$null
	$folderName=$null
	
	$fileName=$item.Name
	#Write-Host $fileName
	if ($fileName.IndexOf($gamenFirst) -gt 0) {
		$folderName=$fileName.substring($fileName.IndexOf($gamenFirst),5)
		#Write-Host $folderName
		#�t�H���_�[�쐬
		if(-! (Test-Path $folderName)){
	  		New-Item $folderName -ItemType Directory
		}
		
		#�t�@�C���ړ�
		#Write-Host $fileName 
		Move-Item $fileName $folderName
	}
}
