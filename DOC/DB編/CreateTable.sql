
drop table if exists payment;
CREATE TABLE payment (
paymentID VARCHAR(6) NOT NULL COMMENT '支払いID',
paymentMonth VARCHAR(6) NOT NULL COMMENT '支払い対象月',
companyID VARCHAR(6) NOT NULL COMMENT '取引先ID',
paymentEmployeeName VARCHAR(20) NOT NULL COMMENT '社員名',
basicAmount VARCHAR(8) NOT NULL COMMENT '基本金額',
overtimeAmount VARCHAR(8) NULL COMMENT '残業額',
paymentDate VARCHAR(8) NOT NULL COMMENT '支払い日（YYYYMMDD形式）',
PRIMARY KEY (paymentID)
) comment'支払管理';
INSERT INTO `ofcfunction` (
    `functionID`, `functionName`, `functionText`, `authority`, `functionLink`,
    `displayNo`, `deleteFlg`, `insertDate`, `updateDate`, `sysType`
) VALUES (
    'P6', 'payment', '&#xe60c;&emsp;支払い管理システム', '1', '/emsm/payment',
    '10', '0', '20250208', '20250208', '2'
);


drop table if exists employee;
create table employee(
employeeID varchar(6) not null primary key comment'社員ID',
employeeName varchar(12) not null comment'社員氏名',
password varchar(50) not null comment'パスワード',
status varchar(1) not null comment'ステータス',
sex varchar(1) comment'性別',
epType varchar(1) not null comment'タイプ',
birthday DateTime comment'生年月日',
age varchar(2) comment'年齢',
joinedDate varchar(8) comment'入社年月日',
joinedTime varchar(2) comment'社齢',
postCode varchar(7) comment'郵便番号',
address varchar(200) comment'住所',
phoneNumber varchar(15) comment'電話番号',
authority varchar(1) comment'権限',
mailAdress varchar(40) not null comment'メール',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日')comment'社員情報';
ALTER TABLE employee DROP COLUMN status;

Insert into employee values
('E001' ,'社員１' ,md5('123456') ,'0' ,'0','0','1986-01-01' ,'34','20190101','2','2310859','横浜市中区','07012344321','0', 'e001@it-softtech.com',date_format(now(),'%Y%m%d') ,null),
('E002' ,'社員２' ,md5('123456') ,'0' ,'0' ,'0','1986-01-02' ,'34','20190101','2','2310859','横浜市中区','07012344322','0', 'e002@it-softtech.com',date_format(now(),'%Y%m%d') ,null),
('E003' ,'社員３' ,md5('123456') ,'0' ,'0' ,'0','1986-01-03' ,'34','20190101','2','2310859','横浜市中区','07012344323','1','e003@it-softtech.com',date_format(now(),'%Y%m%d') ,null);
alter table employee add column department varchar(1) comment'部門';
alter table employee add column personNumber varchar(12) comment'個人番号';
ALTER TABLE ems.employee MODIFY COLUMN status varchar(1) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN sex varchar(1) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN epType varchar(1) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN birthday varchar(8) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN age varchar(2) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN joinedDate varchar(8) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN joinedTime varchar(2) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN postCode varchar(7) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN address varchar(200) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN phoneNumber varchar(15) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN authority varchar(1) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN insertDate varchar(8) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN updateDate varchar(8) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN department varchar(1) DEFAULT NULL;
ALTER TABLE ems.employee MODIFY COLUMN personNumber varchar(12) DEFAULT NULL;
drop table if exists ofcfunction;
create table ofcfunction(
functionID varchar(2) not null primary key comment'機能ID',
functionName varchar(12) not null comment'機能名称',
functionText varchar(200) not null comment'機能表示名称',
authority varchar(1) not null comment'権限',
functionLink varchar(50) not null comment'機能URL',
displayNo varchar(2) comment'表示順',
deleteFlg varchar(1) not null comment'削除フラグ',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
) comment'機能情報';


Insert into ofcfunction values
('S1','employee','&#xe60c;&emsp;社員情報','1','/emsm/initEmployeeInfoList','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('S2','company','&#xe65c;&emsp;取引先情報管理','1','/company','1' ,'0',date_format(now(),'%Y%m%d') ,null),
('S3','contract','&#xe65d;&emsp;契約情報管理','1','/contract','2' ,'1',date_format(now(),'%Y%m%d') ,null),
('S4','workInfo','&#xe672;&emsp;勤怠情報管理','1','/workInfo','3' ,'1',date_format(now(),'%Y%m%d') ,null),
('S5','claim','&#xe681;&emsp;請求情報管理','1','/claim','4' ,'1',date_format(now(),'%Y%m%d') ,null),
('S6','transport','&#xe612;&emsp;交通情報管理','1','/transport','6' ,'1',date_format(now(),'%Y%m%d') ,null),
('S7','salaryInfo','&#xe60c;&emsp;給料情報管理','1','/salaryInfo','7' ,'1',date_format(now(),'%Y%m%d') ,null),
('A7','workdetail','&#xe672;&emsp;勤怠情報','0','/workdetail','1' ,'0',date_format(now(),'%Y%m%d') ,null),
('A8','salarydetail','&#xe60c;&emsp;給料明細','0','/salarydetail','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('A9','password','&#xe696;&emsp;パスワード変更','0','/passwd','99' ,'0',date_format(now(),'%Y%m%d') ,null),
('A1','salarylist','&#xe60c;&emsp;給料リスト','1','/emsm/salarylist','4' ,'0',date_format(now(),'%Y%m%d') ,null),
('A2','employeeEdit','&#xe681;&emsp;社員情報変更','0','/employeeedit','５' ,'0',date_format(now(),'%Y%m%d') ,null),
('A4','welfareList','&#xe681;&emsp;福祉控除リスト','1','/emsm/welfarelist','0' ,'0',date_format(now(),'%Y%m%d') ,null),
('A3','workInfoList','&#xe681;&emsp;勤怠情報リスト','0','/workinfolist','５' ,'0',date_format(now(),'%Y%m%d') ,null),
('A5','salaryList','&#xe681;&emsp;給料明細リスト','0','/salarylist','５' ,'0',date_format(now(),'%Y%m%d') ,null),
('A6','workdetailli','&#xe60c;&emsp;勤怠リスト','1','/emsm/workdetaillist','3' ,'0',date_format(now(),'%Y%m%d') ,null),
('B1','expenses','&#xe60c;&emsp;一般経費','1','/emsm/initExpensesManagement','1' ,'0',date_format(now(),'%Y%m%d') ,null),
('B2','basesalary','&#xe60c;&emsp;基本給情報リスト','1','/emsm/initBaseSalaryList','2' ,'0',date_format(now(),'%Y%m%d') ,null),
('W1','welfarefee','&#xe60c;&emsp;マスタ_厚生保険料','1','/emsm/initWelfarefeeInfoList','8' ,'0',date_format(now(),'%Y%m%d') ,null),
('W2','welfarebaby','&#xe60c;&emsp;マスタ_厚生子育徴収率','1','/emsm/initWelfareBabyInfoList','8' ,'0',date_format(now(),'%Y%m%d') ,null),
('S9','emplyinsrate','&#xe60c;&emsp;雇用保険率テーブル','1','/emsm/initEmplyinsrateInfoList','9' ,'0',date_format(now(),'%Y%m%d') ,null),
('M1','incomeTax','&#xe60c;&emsp;所得税と住民税マスター','1','/emsm/initIncomeTaxInfoList','9' ,'0',date_format(now(),'%Y%m%d') ,null);
insert into ofcfunction('functionID','functionName','functionText','authority','functionLink','deleteFlg')
values
('M1','incomeTax','&#xe60c;&emsp;消費税テーブル','1','/emsm/initIncomeTaxInfoList','0');



alter table ofcfunction add column sysType varchar(1);

drop table if exists company;
create table company(
companyID varchar(6) not null primary key comment'取引先ID',
companyName varchar(50) not null comment'取引先名称',
companyType varchar(1) comment'取引先種類',
postCode varchar(7) not null comment'郵便番号',
address varchar(100) not null comment'住所',
basicContractDate varchar(8) comment'基本契約日',
phoneNumber varchar(15) not null comment'電話番号',
contactName varchar(20) comment'連絡先名',
mail varchar(20) comment'メール',
contractStatus varchar(1) comment'ステータス',
level varchar(1) comment'評判レベル',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日')comment'取引先情報';
Insert into company values
('C001','木の葉隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340000','火影綱手', 'aaa@gmail.com','0','0',date_format(now(), '%Y%m%d'), null),
('C002','霧隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340001','水影照美冥', 'bbb@gmail.com','0','0',date_format(now(), '%Y%m%d'), null),
('C003','岩隠れの里株式会社','0','2310859','横浜市西区','20200101','07012340001','土影大野木', 'ccc@gmail.com','0','0',date_format(now(), '%Y%m%d'), null);

drop table if exists contract;
create table contract(
contractID varchar(10) not null primary key comment'契約ID',
contractName varchar(50) not null comment'契約名称',
employeeID varchar(6) not null comment'社員ID',
companyID varchar(6) not null comment'取引先ID',
price int(7) not null comment'単価',
payOff varchar(1) not null comment'精算タイプ',
lowerTime varchar(3) not null comment'契約下限',
lowerPrice int(6) not null comment'控除単価',
upperTime varchar(3) not null comment'契約上限',
upperPrice int(6) not null comment'残業単価',
contractBeginDate varchar(8)  not null comment'契約開始日',
contractEndDate varchar(8) comment'契約終了日',
paymentTerm varchar(2) not null comment'支払サイト',
postNeed varchar(1) comment'原本郵送フラグ',
timeReportPath varchar(255) not null comment'タイムレポートパス',
invoice varchar(50) comment'請求書名称',
status varchar(1) not null comment'進行ステータス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
)comment'契約情報';

Insert into contract values
('CT001','火遁忍術開発支援','E001','C001',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 火遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d')),
('CT002','水遁忍術開発支援','E002','C002',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 水遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d')),
('CT003','水遁忍術開発支援','E003','C003',600000,'0',150,1000,200,1000,'20210101','20210131','10','0','D:/tmp/work',' 土遁忍術開発支援', '1',date_format(now(), '%Y%m%d'), date_format(now(), '%Y%m%d'));

drop table if exists workinfo;
  CREATE TABLE ems.workinfo (
    contractID    VARCHAR(10)  NOT NULL COMMENT '契約ID',
    workMonth     VARCHAR(6)   NOT NULL COMMENT '稼働月 (YYYYMM)',
    workStartDay  VARCHAR(8)   NOT NULL COMMENT '稼働開始日',
    workEndDay    VARCHAR(8)   NOT NULL COMMENT '稼働最終日',
    workTime      FLOAT        NOT NULL DEFAULT 0 COMMENT '稼働時間 (h)',
    workInfoFile  VARCHAR(50)           COMMENT '稼働表パス',
    insertDate    VARCHAR(8)            COMMENT '作成日',
    updateDate    VARCHAR(8)            COMMENT '更新日',
    PRIMARY KEY (contractID, workMonth)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COMMENT='勤怠情報';

drop table if exists claim;
create table claim(
claimID varchar(10) not null primary key comment'請求ID',
contractID varchar(10) not null comment'契約ID',
claimMonth varchar(8) not null comment'請求月',
workTime int(3) comment'稼働時間',
exceTime int(3) comment'過稼働時間',
addpayOff int(7) comment'加算額',
deficiTime int(3) comment'不足稼働時間',
minusPayOff int(7) comment'減算額',
transport int(7) comment'交通費',
businessTrip int(7) comment'出張旅費',
taxRate int(2) comment'消費税率',
consumpTax int(7) comment'消費税',
sum int(8) comment'合計',
specialClaim int(8) comment'特別請求',
claimStatus varchar(1) not null comment'請求ステータス',
insertDate varchar(8) comment'作成日',
updateDate varchar(8) comment'更新日'
)comment'請求情報';

drop table if exists salaryinfo;
create table salaryinfo(
employeeID varchar(6) not null  comment'社員ID',
month  varchar(6) not null  comment'対象月',
paymentDate  varchar(8) not null comment'支払日',
base int(8) not null comment'基本給',
overTime int(8) comment'残業時間',
shortage int(8) comment'不足時間',
overTimePlus int(8) comment'残業加算',
shortageReduce int(8) comment'稼働不足減',
transportExpense int(6) comment'交通費',
specialAddition int(6) comment'特別加算',
allowancePlus int(6) comment'手当加算',
allowanceReduce int(6) comment'手当減算',
allowanceReason  varchar(50) comment'手当理由',
welfarePensionSelf int(6) comment'厚生控除個人',
welfarePensionComp int(6) comment'厚生控除会社',
welfareHealthComp int(6) comment'厚生健康控除会社',
welfareHealthSelf int(6) comment'厚生健康控除個人',
welfareBaby int(6) comment'厚生控除子育(会社)',
eplyInsSelf int(6) comment'雇用保険個人負担',
eplyInsComp int(6) comment'雇用保険会社負担',
eplyInsWithdraw int(6) comment'雇用保拠出金（会社)',
wkAcccpsIns int(6) comment'労災保険（会社負担のみ）',
withholdingTax int(6) comment'源泉控除',
municipalTax int(6) comment'住民税控除',
rental int(6) comment'社宅家賃控除',
rentalMgmtFee int(6) comment'社宅共益費控除',
specialReduce int(6) comment'特別控除',
sum int(9) not null comment'総額',
totalFee int(9) not null comment'総費用',
remark varchar(200) comment'備考',
deleteFlg  varchar(1) comment'削除フラグ',
insertDate  varchar(8) comment'作成日',
updateDate  varchar(8) comment'更新日',
primary key(employeeID,month)
)comment'給料情報';
Insert into salaryinfo (employeeID,month,paymentDate,base,overTime,shortage,overTimePlus,shortageReduce,transportExpense,specialAddition,allowancePlus,allowanceReduce,allowanceReason,welfarePensionSelf,welfarePensionComp,welfareBaby,eplyInsSelf,eplyInsComp,eplyInsWithdraw,wkAcccpsIns,withholdingTax,municipalTax,rental,rentalMgmtFee,specialReduce,sum,totalFee,remark,deleteFlg,insertDate,updateDate)
values ('E001','202001','20200215',60000,0,0,0,0,0,0,0,0,'',0,0,0,0,0,0,0,0,0,0,0,0,60000,60000,'','0','20210122','20210122');

alter table ems.salaryinfo CHANGE welfareSelf welfarePensionSelf int DEFAULT 0 COMMENT '厚生年金控除個人';
alter table ems.salaryinfo add column welfarePensionComp int DEFAULT 0 COMMENT '厚生年金控除会社' after welfarePensionSelf;
alter table ems.salaryinfo CHANGE welfareComp welfareHealthComp int DEFAULT 0 COMMENT '厚生健康控除会社';
alter table ems.salaryinfo add column welfareHealthSelf int DEFAULT 0 COMMENT '厚生健康控除個人' after welfarePensionSelf;

drop table if exists transport;
CREATE TABLE ems.transport (
    employeeID VARCHAR(6) NOT NULL COMMENT '社員ID',
    workMonth VARCHAR(6) NOT NULL COMMENT '対象月',
    startDate VARCHAR(8) NOT NULL COMMENT '開始日',
    startStation VARCHAR(20) NOT NULL COMMENT '起点駅',
    endStation VARCHAR(20) NOT NULL COMMENT '終点駅',
    transportFacility VARCHAR(20) NOT NULL COMMENT '交通機関(代表)',
    transportExpense1 INT NOT NULL COMMENT '定期券金額(1ヶ月)',
    midStation1 VARCHAR(20) COMMENT '中間駅1',
    transportFacility1 VARCHAR(20) COMMENT '交通機関1',
    midStation2 VARCHAR(20) COMMENT '中間駅2',
    transportFacility2 VARCHAR(20) COMMENT '交通機関2',
    midStation3 VARCHAR(20) COMMENT '中間駅3',
    transportFacility3 VARCHAR(20) COMMENT '交通機関3',
    transportExpense2 FLOAT COMMENT '定期券金額(2ヶ月)',
    transportExpense3 FLOAT COMMENT '定期券金額(3ヶ月)',
    transport INT COMMENT '交通費',
    businessTrip INT COMMENT '出張旅費',
    BusinessTripName VARCHAR(50) COMMENT '出張旅費ファイル',
    status VARCHAR(1) NOT NULL COMMENT '使用ステータス',
    insertDate VARCHAR(8) COMMENT '作成日',
    updateDate VARCHAR(8) COMMENT '更新日',
    PRIMARY KEY (employeeID, workMonth)
) ENGINE=InnoDB 
  DEFAULT CHARSET=utf8mb4 
  COMMENT='交通情報';
insert into transport values
("E001","202104","20210101","西川口駅","銀座駅","京浜東北線",3000,"赤羽駅","埼京線","新宿駅","埼京線","新宿駅","丸の内線",0,0,10000,2500,'D:\\TName\\',"0",date_format(now(), '%Y%m%d'), null);

drop table if exists welfareinfo;
create table welfareinfo(
employeeID varchar(6) not null comment'社員ID',
startDate varchar(8) not null comment'控除開始日',
base int(8) not null comment'基本給',
welfarePensionSelf int(6) comment'厚生年金控除個人',
welfarePensionComp int(6) comment'厚生年金控除会社',
welfareHealthComp int(6) comment'厚生健康控除会社',
welfareHealthSelf int(6) comment'厚生健康控除個人',
welfareBaby int(6) comment'厚生控除子育(会社)',
eplyInsSelf int(6) comment'雇用保険個人負担',
eplyInsComp int(6) comment'雇用保険会社負担',
eplyInsWithdraw int(6) comment'雇用保拠出金（会社)',
wkAcccpsIns int(6) comment'労災保険（会社負担のみ）',
withholdingTax int(6) comment'源泉控除',
municipalTax1 int(6) comment'住民税控除一月',
municipalTax2 int(6) comment'住民税控除二月',
municipalTax3 int(6) comment'住民税控除三月',
municipalTax4 int(6) comment'住民税控除四月',
municipalTax5 int(6) comment'住民税控除五月',
municipalTax6 int(6) comment'住民税控除六月',
municipalTax7 int(6) comment'住民税控除七月',
municipalTax8 int(6) comment'住民税控除八月',
municipalTax9 int(6) comment'住民税控除九月',
municipalTax10 int(6) comment'住民税控除十月',
municipalTax11 int(6) comment'住民税控除十一月',
municipalTax12 int(6) comment'住民税控除十二月',
rental int(6) comment'社宅家賃控除',
rentalMgmtFee int(6) comment'社宅管理費控除',
status varchar(1) not null comment'控除ステータス',
insertDate varchar(8) comment'作成日',
insertEmployee varchar(6) comment'作成者',
updateDate varchar(8) comment'更新日',
updateEmployee varchar(6) comment'更新者',
primary key(employeeID,startDate)
)comment'福祉情報';

drop table if exists expenses;
create table expenses
(
    expensesID         varchar(10) NOT NULL PRIMARY KEY COMMENT '経費ID',
    accrualDate        varchar(8)  not null comment '発生日',
    cost               int(6)      not null not null comment '金額',
    tantouName         varchar(6)  not null comment '担当者',
    confirmStaus       varchar(1)  not null comment '承認ステータス',
    confirmDate        varchar(8)  not null comment '承認日',
    confirmerID        varchar(6)  not null comment '承認者ID',
    confirmerName      varchar(6)  not null comment '承認者',
    stmtlStaus         varchar(1)  not null comment '精算ステータス',
    stmtlDate          varchar(8)  not null comment '精算日',
    paymentType        varchar(1)  not null comment '出金タイプ',
    insertID           varchar(6)  not null comment '作成者ID',
    insertDate         varchar(8)  not null comment '作成日',
    updateID           varchar(6)  not null comment '更新者ID',
    updateDate         varchar(8)  not null comment '更新日',
    expensesType       varchar(2)  not null COMMENT '経費種別',
    expensesTypeDetail varchar(2)  not null COMMENT '経費種別明細',
    deleteFlg          varchar(2)  not null COMMENT '削除フラグ',
    happenAddress      varchar(50) not null COMMENT '場所',
    remark             varchar(50) not null COMMENT '備考'
) comment '一般経費';

drop table if exists m_basesalary;
create table m_basesalary(
baseSalary int not null comment'基本給',
minusHour int not null comment'残業不足時間',
plusHour int not null comment'残業時間',
wkPeriodFrom int not null comment'稼働期間From',
wkPeriodTo int not null comment'稼働期間To',
overtimePay DECIMAL(7,1) not null comment'残業単価',
insufficienttimePay DECIMAL(7,1) not null comment'控除単価',
status int not null comment'利用ステータス',
insertDate varchar(8) not null comment'作成日',
updateDate varchar(8) not null comment'更新日',
baseSalaryID varchar(8) not null PRIMARY KEY comment'基本給ID',
employeeID varchar(8) not null comment'社員ID'
)comment'基本給_マスタ機能';
ALTER TABLE m_basesalary ADD year varchar(4) NOT NULL comment'対象年度';

drop table if exists m_welfarefee;
create table m_welfarefee(
welfarefeeID varchar(10) not null PRIMARY KEY comment'厚生保険料ID',
year varchar(8) not null comment'対象年度',
area varchar(8) not null comment'対象エリア',
standSalary int not null comment'標準報酬',
salaryFrom int not null comment'給料From',
salaryTo int not null comment'給料To',
notCareRatio DECIMAL(6,5) not null comment'介護必要ない料率',
careRatio DECIMAL(6,5) not null comment'介護必要料率',
annuityRatio DECIMAL(6,5) not null comment'厚生年金保険料率',
contributionRate DECIMAL(6,5) not null comment'厚生子育拠出金率',
status int not null comment'利用ステータス',
insertDate varchar(8) not null comment'作成日',
updateDate varchar(8) not null comment'更新日'
) comment '厚生保険料マスタ';


drop table if exists m_emplyinsrate;
create table m_emplyinsrate(
emplyinsrateID varchar(10) not null PRIMARY KEY comment'雇用保険ID',
year varchar(8) not null comment'対象年度',
laborBurdenRate DECIMAL(6,5) not null comment'雇用保険労働者負担料率‰',
employerBurdenRate DECIMAL(6,5) not null comment'雇用保険事業主負担料率‰',
employmentInsuranceRate DECIMAL(6,5) not null comment'雇用保険料率‰',
industrialAccidentInsuranceRate DECIMAL(6,5) not null comment'労災保険料率(全額事業主)‰',
laborInsuranceRate DECIMAL(6,5) not null comment'労働保険料率‰',
contributionRate DECIMAL(6,5) not null comment'一般拠出金料率(全額事業主)‰',
status int not null comment'利用ステータス',
insertDate varchar(8) not null comment'作成日',
updateDate varchar(8) not null comment'更新日'
) comment '雇用保険率テーブル';


drop table if exists m_incometax;
create table m_incometax(
incomeTaxID varchar(10) NOT NULL PRIMARY KEY COMMENT '所得税ID',
employeeID varchar(8) NOT NULL COMMENT '社員ID',
year varchar(8) NOT NULL COMMENT '対象年度',
incomeTax1 int NOT NULL COMMENT '一月所得税',
incomeTax2 int NOT NULL COMMENT '二月所得税',
incomeTax3 int NOT NULL COMMENT '三月所得税',
incomeTax4 int NOT NULL COMMENT '四月所得税',
incomeTax5 int NOT NULL COMMENT '五月所得税',
incomeTax6 int NOT NULL COMMENT '六月所得税',
incomeTax7 int NOT NULL COMMENT '七月所得税',
incomeTax8 int NOT NULL COMMENT '八月所得税',
incomeTax9 int NOT NULL COMMENT '九月所得税',
incomeTax10 int NOT NULL COMMENT '十月所得税',
incomeTax11 int NOT NULL COMMENT '十一月所得税',
incomeTax12 int NOT NULL COMMENT '十二月所得税',
residentTax1 int NOT NULL COMMENT '一月住民税',
residentTax2 int NOT NULL COMMENT '二月住民税',
residentTax3 int NOT NULL COMMENT '三月住民税',
residentTax4 int NOT NULL COMMENT '四月住民税',
residentTax5 int NOT NULL COMMENT '五月住民税',
residentTax6 int NOT NULL COMMENT '六月住民税',
residentTax7 int NOT NULL COMMENT '七月住民税',
residentTax8 int NOT NULL COMMENT '八月住民税',
residentTax9 int NOT NULL COMMENT '九月住民税',
residentTax10 int NOT NULL COMMENT '十月住民税',
residentTax11 int NOT NULL COMMENT '十一月住民税',
residentTax12 int NOT NULL COMMENT '十二月住民税',
status int NOT NULL COMMENT '利用ステータス',
insertDate varchar(8) NOT NULL COMMENT '作成日',
updateDate varchar(8) NOT NULL COMMENT '更新日'
) comment '所得税と住民税マスター管理';


drop table if exists m_department;
create table m_department(
    departmentID varchar(2) not null primary key comment'部門ID' ,
    departmentName varchar(5) not null comment '部門名称'
) comment '部門_マスタ機能';
Insert into m_department values ('1','開発一部') , ('2','開発二部') , ('3','管理部');


drop table if exists m_eptype ;
create table m_eptype (
    epTypeID varchar (2) not null primary key comment '社員タイプID' ,
    epTypeName varchar (5) not null comment '社員タイプ名称'
)comment '社員タイプ_マスタ機能' ;
Insert into m_eptype values ('0','正社員') , ('1','契約社員') , ('2','個人');


--employee table 社員タイプの桁数変更
alter table employee modify epType varchar(2)DEFAULT NULL COMMENT '社員タイプ' ;

--employee table 部門の桁数変更
alter table employee modify department varchar(2)DEFAULT NULL COMMENT '部門' ;

--m_eptype table 各columnの桁数変更
alter table m_eptype modify epTypeID varchar(2) NOT NULL COMMENT '社員タイプID';
alter table m_eptype modify epTypeName varchar(5) NOT NULL COMMENT '社員タイプ名称';

--m_department table 各columnの桁数変更
alter table m_department modify departmentID varchar(2) NOT NULL COMMENT '部門ID';
alter table m_department modify departmentName varchar(5) NOT NULL COMMENT '部門名称';

drop table if exists m_basesalary;
create table m_basesalary(
baseSalary int not null comment'基本給',
year varchar(4) not null comment'対象年度',
minusHour int not null comment'残業不足時間',
plusHour int not null comment'残業時間',
wkPeriodFrom int not null comment'稼働期間From',
wkPeriodTo int not null comment'稼働期間To',
overtimePay DECIMAL(7,1) not null comment'残業単価',
insufficienttimePay DECIMAL(7,1) not null comment'控除単価',
status int not null comment'利用ステータス',
insertDate varchar(8) not null comment'作成日',
updateDate varchar(8) not null comment'更新日',
baseSalaryID varchar(8) not null PRIMARY KEY comment'基本給ID',
employeeID varchar(8) not null comment'社員ID'
)comment'基本給_マスタ機能';

drop table if exists m_welfarebabyrate;
create table m_welfarebabyrate(
    rateID int not null primary key comment'徴収ID',
    year varchar(4) not null comment'対処年度',
    area varchar(20) not null comment'対処エリア',
    rate decimal(3,2) NOT NULL COMMENT '徴収率',
    status int NOT NULL COMMENT '利用ステータス',
    insertDate varchar(8) NOT NULL COMMENT '作成日',
    updateDate varchar(8) NOT NULL COMMENT '更新日'
    )comment 'マスタ＿厚生子育徴収率';


alter table m_welfarefee modify notCareRatio decimal(6,3) NOT NULL COMMENT '介護必要ない料率';
alter table m_welfarefee modify careRatio decimal(6,3) NOT NULL COMMENT '介護必要料率';
alter table m_welfarefee modify annuityRatio decimal(6,3) NOT NULL COMMENT '厚生年金保険料率';

alter table salaryinfo modify overTime float NOT NULL DEFAULT '0' COMMENT '残業時間';
alter table salaryinfo modify shortage float NOT NULL DEFAULT '0' COMMENT '不足時間';

alter table m_welfarefee drop column contributionRate;
alter table m_basesalary drop column minusHour;
alter table m_basesalary drop column plusHour;

alter table m_emplyinsrate drop column employmentInsuranceRate;
alter table m_emplyinsrate drop column laborInsuranceRate;


drop table if exists yukyu;
create table yukyu (
    employeeID varchar(6),
    nendo varchar(4),
    totalDay varchar(2),
    usedDay varchar(2),
    insertDate varchar(8),
    updateDate varchar(8),
    primary key (employeeID,nendo)
);
insert into yukyu values
('E001','2024','18','12','2024-4-8','2024-4-8'),
('E002','2023','20','10','2024-4-8','2024-4-8');

---------プロシージャ------
CREATE  PROCEDURE `makewelfare`(in gamenMode VarChar(1)
,in employeeID1 VarChar(6)
,in startDate1 VarChar(8)
,in base Int
,in welfarePensionSelf1 Int
,in welfarePensionComp1 Int
,in welfareHealthComp1 Int
,in welfareHealthSelf1 Int
,in welfareBaby1 Int
,in eplyInsSelf1 Int
,in eplyInsComp1 Int
,in eplyInsWithdraw1 Int
,in wkAcccpsIns1 Int
,in withholdingTax1 Int
,in municipalTax1 Int
,in rental1 Int
,in rentalMgmtFee1 Int
,in status1 VarChar(1)
,in insertEmployee1 VarChar(6)
,in updateEmployee1 VarChar(6)
)
BEGIN

IF (gamenMode='2') then
update welfareinfo set
base=base,
welfarePensionSelf=welfarePensionSelf1,
welfarePensionComp=welfarePensionComp1,
welfareHealthComp=welfareHealthComp1,
welfareHealthSelf=welfareHealthSelf1,
welfareBaby=welfareBaby1,
eplyInsSelf=eplyInsSelf1,
eplyInsComp=eplyInsComp1,
eplyInsWithdraw=eplyInsWithdraw1,
wkAcccpsIns=wkAcccpsIns1,
withholdingTax=withholdingTax1,
municipalTax=municipalTax1,
rental=rental1,
rentalMgmtFee=rentalMgmtFee1,
status=status1,
insertDate=CURDATE(),
insertEmployee=insertEmployee1,
updateDate=CURDATE(),
updateEmployee=updateEmployee1
where welfareinfo.employeeID=employeeID1
and   welfareinfo.startDate=startDate1;

ELSEIF (gamenMode='1') then
insert into welfareinfo(
employeeID
,startDate
,base
,welfarePensionSelf
,welfarePensionComp
,welfareHealthComp
,welfareHealthSelf
,welfareBaby
,eplyInsSelf
,eplyInsComp
,wkAcccpsIns
,eplyInsWithdraw
,withholdingTax
,municipalTax
,rental
,rentalMgmtFee
,status
,insertDate
,insertEmployee
,updateDate
,updateEmployee
)
VALUES(employeeID1
,startDate1
,base
,welfarePensionSelf1
,welfarePensionComp1
,welfareHealthComp1
,welfareHealthSelf1
,welfareBaby1
,eplyInsSelf1
,eplyInsComp1
,eplyInsWithdraw1
,wkAcccpsIns1
,withholdingTax1
,municipalTax1
,rental1
,rentalMgmtFee1
,status1
,CURDATE()
,insertEmployee1
,CURDATE()
,updateEmployee1);
END IF;
END


--新規年末調整テーブル
DROP TABLE IF EXISTS ems.adjustmentDetail;
CREATE TABLE ems.adjustmentDetail (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
    `employeeEmail` VARCHAR(255) NOT NULL COMMENT '社員メール',
    `year` VARCHAR(4) NOT NULL COMMENT '年度',
    `uploadStatus` VARCHAR(50) COMMENT 'アップロードステータス',
    `adjustmentStatus` VARCHAR(50) DEFAULT '0' COMMENT '調整ステータス',
    `insertDate` DATETIME COMMENT '作成日時',
    `updateDate` DATETIME COMMENT '更新日時',
    FOREIGN KEY (`employeeID`) REFERENCES `employee`(`employeeID`) ON DELETE CASCADE,
    INDEX idx_year_employee (`year`, `employeeID`),
    INDEX idx_email (`employeeEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='年末調整詳細テーブル';

DROP TABLE IF EXISTS ems.adjustmentFile;
CREATE TABLE ems.adjustmentFile (
    `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
    `fileType` VARCHAR(255) NOT NULL COMMENT 'ファイルタイプ',
    `fileYear` INT NOT NULL COMMENT 'ファイル年度',
    `fileName` VARCHAR(255) NOT NULL COMMENT 'ファイル名',
    `fileStatus` VARCHAR(50) NOT NULL COMMENT 'ファイルステータス',
    `filePath` VARCHAR(255) NOT NULL COMMENT 'ファイルパス',
    `insertDate` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    `updateDate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    PRIMARY KEY (employeeID, fileType, fileYear, fileName),
    FOREIGN KEY (`employeeID`) REFERENCES `employee`(`employeeID`) ON DELETE CASCADE,
    INDEX idx_employee_year (`employeeID`, `fileYear`),
    INDEX idx_file_type (`fileType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='年末調整ファイルテーブル';

DROP TABLE IF EXISTS ems.adjustmentRequestFiles;
CREATE TABLE ems.adjustmentRequestFiles (
    `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `fileName` VARCHAR(255) NOT NULL COMMENT 'ファイル名',
    `fileYear` INT NOT NULL COMMENT 'ファイル年度',
    `fileULStatus` VARCHAR(50) NOT NULL DEFAULT '0' COMMENT 'アップロードステータス',
    `filePath` VARCHAR(255) NOT NULL COMMENT 'ファイルパス',
    `insertDate` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    `updateDate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    UNIQUE KEY uk_filename_year (`fileName`, `fileYear`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='年末調整要求ファイルテーブル';


--年末調整画面をofcfunction表に挿入する
ALTER TABLE ems.ofcfunction
MODIFY COLUMN functionName VARCHAR(20);

INSERT INTO ems.ofcfunction (
    `functionID`,
    `functionName`,
    `functionText`,
    `authority`,
    `functionLink`,
    `displayNo`,
    `deleteFlg`,
    `insertDate`,
    `updateDate`,
    `sysType`
) VALUES
    ('B4', 'Adjustment', '&#xe681;&emsp;年末調整', '0', '/adjustment', '6', '0', DATE_FORMAT(CURDATE(), '%Y%m%d'), DATE_FORMAT(CURDATE(), '%Y%m%d'), '1'),
    ('B5', 'adjustmentList', '&#xe60c;&emsp;年末調整', '1', '/emsm/adjustmentList', '10', '0', DATE_FORMAT(CURDATE(), '%Y%m%d'), DATE_FORMAT(CURDATE(), '%Y%m%d'), '2');


-- 経費種別マスタ表
drop table if exists ems.expenses;
CREATE TABLE ems.m_expenses (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    expensesType VARCHAR(2) NOT NULL COMMENT '経費種別コード',
    expensesTypeName VARCHAR(50) NOT NULL COMMENT '経費種別名称',
    expenseName VARCHAR(50) NOT NULL COMMENT '経費名称',
    deleteFlg CHAR(1) NOT NULL DEFAULT '0' COMMENT '0:未削除, 1:削除',
    createdBy VARCHAR(50) NOT NULL COMMENT '作成者',
    updatedBy VARCHAR(50) NOT NULL COMMENT '更新者',
    insertDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updateDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時'
) COMMENT='経費種別マスタ';

-- 経費管理表
drop table if exists ems.m_expenses;
CREATE TABLE ems.expenses (
    expensesID INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '経費ID',
    accrualDate DATE NOT NULL COMMENT '発生日',
    cost DECIMAL(15, 2) NOT NULL COMMENT '金額',
    tantouName VARCHAR(6) NOT NULL COMMENT '担当者',
    settlementType CHAR(1) DEFAULT NULL COMMENT '0:現金, 1:口座',
    settlementDate DATE DEFAULT NULL COMMENT '精算日',
    expensesType VARCHAR(2) NOT NULL COMMENT '経費種別コード',
    m_expenses_id INT DEFAULT NULL COMMENT '経費種別明細ID（m_expensesのid）',
    deleteFlg CHAR(1) NOT NULL DEFAULT '0' COMMENT '0:未削除, 1:削除',
    happenAddress VARCHAR(255) NOT NULL COMMENT '用途',
    receiptPath VARCHAR(255) DEFAULT NULL COMMENT '領収書画像ファイルのパス',
    
    CONSTRAINT fk_expenses_m_expenses_id FOREIGN KEY (m_expenses_id) REFERENCES m_expenses(id)
) COMMENT='経費管理';   


--経費管理画面をofcfunction表に挿入する
INSERT INTO ems.ofcfunction (
    `functionID`,
    `functionName`,
    `functionText`,
    `authority`,
    `functionLink`,
    `displayNo`,
    `deleteFlg`,
    `insertDate`,
    `updateDate`,
    `sysType`
) VALUES
    ('B8', 'expenseList', '&#xe65d;&emsp;経費管理', '1', '/emsm/expenseList', '11', '0', DATE_FORMAT(NOW(), '%Y%m%d'), DATE_FORMAT(NOW(), '%Y%m%d'), '2');
--経費種別管理画面をofcfunction表に挿入する
INSERT INTO ems.ofcfunction (
    `functionID`,
    `functionName`,
    `functionText`,
    `authority`,
    `functionLink`,
    `displayNo`,
    `deleteFlg`,
    `insertDate`,
    `updateDate`,
    `sysType`
) VALUES
    ('B9', 'expenseType', '&#xe65d;&emsp;経費種別管理', '1', '/emsm/expenseType', '16', '0', DATE_FORMAT(NOW(), '%Y%m%d'), DATE_FORMAT(NOW(), '%Y%m%d'), '2');

--m_welfarebabyrateのidを更新
ALTER TABLE ems.m_welfarebabyrate MODIFY COLUMN rateID VARCHAR(10) NOT NULL COMMENT '徴収ID';

--新取引先画面をofcfunction表に挿入する
INSERT INTO ofcfunction (
    functionID, functionName, functionText, authority, functionLink,
    displayNo, deleteFlg, insertDate, updateDate, sysType
) VALUES (
    'T1', 'torihiki', '&#xe65c;&emsp;取引先', 1, '/emsm/torihiki',
    2, 0, '20250605', '20250605', 2
);


-- 銀行口座テーブル
CREATE TABLE ems.bankAccount (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主キー',
    transactionDate DATE NOT NULL COMMENT '取引日付',
    transactionType VARCHAR(50) COMMENT '取引区分',
    description VARCHAR(200) COMMENT '摘要',
    withdrawal DECIMAL(15,2) DEFAULT 0 COMMENT '出金額',
    deposit DECIMAL(15,2) DEFAULT 0 COMMENT '入金額',
    balance DECIMAL(15,2) NOT NULL COMMENT '残高',
    remarks TEXT COMMENT 'メモ',
    insertDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '挿入日',
    path VARCHAR(500) COMMENT 'ファイルパス',
    PRIMARY KEY (id),
    INDEX idx_transactionDate (transactionDate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='銀行口座テーブル';

ALTER TABLE ems.bankAccount
ADD UNIQUE idx_unique_tx (
  transactionDate,
  transactionType,
  withdrawal,
  deposit,
  description
);

