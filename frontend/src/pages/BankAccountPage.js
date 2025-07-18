import React, { useState, useEffect } from 'react';
import SearchForm from '../components/BankAccount/SearchForm';
import BankAccountTable from '../components/BankAccount/BankAccountTable';
import {
  fetchAccounts,
  updateRemarks,
  uploadCsvFile,
} from '../services/bankAccountApi';
import '../styles/BankAccount.css';  // 添加样式导入

function BankAccountPage() {
  const [accounts, setAccounts]       = useState([]);
  const [totalWithdrawal, setTotalWithdrawal] = useState(0);
  const [totalDeposit, setTotalDeposit]       = useState(0);
  const [finalBalance, setFinalBalance]       = useState(0);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate]     = useState('');
  const [uploading, setUploading] = useState(false);

  useEffect(() => {
    loadAccounts();
  }, []);

  const loadAccounts = async (params = {}) => {
    try {
      const data = await fetchAccounts(params);
      setAccounts(data.accountList || []);
      setTotalWithdrawal(data.totalWithdrawal || 0);
      setTotalDeposit(data.totalDeposit || 0);
      setFinalBalance(data.finalBalance || 0);
    } catch (err) {
      console.error('口座情報取得失敗:', err);
    }
  };

  const handleSearch = ({ startDate, endDate }) => {
    setStartDate(startDate);
    setEndDate(endDate);
    loadAccounts({ startDate, endDate });
  };

  const handleUpdateRemarks = async (id, remarks) => {
    try {
      await updateRemarks(id, remarks);
      await loadAccounts({ startDate, endDate });
    } catch (err) {
      console.error('備考更新失敗:', err);
      alert('更新に失敗しました');
    }
  };

  const handleFileUpload = async (file) => {
    if (!file) {
      alert('ファイルを選択してください。');
      return;
    }
    try {
      setUploading(true);
      const result = await uploadCsvFile(file);

      const msgLines = [
          'アップロード完了',
          `ファイル: ${result.uploadedFile || result.rawFile || '(不明)'}`,
          `登録: ${result.inserted ?? 0} 件`,
          `重複: ${result.duplicates ?? 0} 件`,
          `エラー: ${result.errors ?? 0} 件`,
        ];
      if (result.duplicateFile) msgLines.push(`重複ファイル: ${result.duplicateFile}`);
      if (result.errorFile)     msgLines.push(`エラーファイル: ${result.errorFile}`);
      alert(msgLines.join('\n'));

      await loadAccounts({ startDate, endDate });
    } catch (err) {
      console.error('アップロード失敗:', err);
      alert('アップロードに失敗しました: ' + (err?.response?.data?.error || err.message));
    } finally {
      setUploading(false);
    }
  };

  return (
    <div className="table-container">
      <h1>銀行口座管理</h1>

      <SearchForm
        startDate={startDate}
        endDate={endDate}
        onSearch={handleSearch}
        onFileUpload={handleFileUpload}
        uploading={uploading}
      />

      <BankAccountTable
        accounts={accounts}
        totalWithdrawal={totalWithdrawal}
        totalDeposit={totalDeposit}
        finalBalance={finalBalance}
        onUpdateRemarks={handleUpdateRemarks}
      />
    </div>
  );
}

export default BankAccountPage;