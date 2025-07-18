import React, { useState } from 'react';

function BankAccountTable({ accounts, totalWithdrawal, totalDeposit, finalBalance, onUpdateRemarks }) {
  const [editingId, setEditingId] = useState(null);
  const [editingRemarks, setEditingRemarks] = useState('');

  const handleEdit = (account) => {
    setEditingId(account.id);
    setEditingRemarks(account.remarks || '');
  };

  const handleSave = (accountId) => {
    onUpdateRemarks(accountId, editingRemarks);
    setEditingId(null);
    setEditingRemarks('');
  };

  const handleCancel = () => {
    setEditingId(null);
    setEditingRemarks('');
  };

  const formatCurrency = (amount) => {
    return amount ? `${amount.toLocaleString()}円` : '';
  };

  return (
    <table>
      <thead>
        <tr>
          <th>日付</th>
          <th>取引区分</th>
          <th>摘要</th>
          <th>出金額</th>
          <th>入金額</th>
          <th>残高</th>
          <th>備考</th>
          <th>編集</th>
        </tr>
      </thead>
      <tbody>
        {accounts.map((account) => (
          <tr key={account.id}>
            <td className="col-transactionDate">{account.transactionDate}</td>
            <td className="col-transactionType">{account.transactionType}</td>
            <td className="col-description">{account.description}</td>
            <td className="col-withdrawal text-right text-red">
              {account.withdrawal > 0 && formatCurrency(account.withdrawal)}
            </td>
            <td className="col-deposit text-right text-blue">
              {account.deposit > 0 && formatCurrency(account.deposit)}
            </td>
            <td className="col-balance text-right">{formatCurrency(account.balance)}</td>
            <td className="col-remarks remarks-cell">
              {editingId === account.id ? (
                <input
                  type="text"
                  className="remarks-input"
                  value={editingRemarks}
                  onChange={(e) => setEditingRemarks(e.target.value)}
                  autoFocus
                />
              ) : (
                account.remarks || ''
              )}
            </td>
            <td className="col-edit">
              {editingId === account.id ? (
                <>
                  <button className="save-btn" onClick={() => handleSave(account.id)}>
                    保存
                  </button>
                  <button className="cancel-btn" onClick={handleCancel}>
                    キャンセル
                  </button>
                </>
              ) : (
                <button className="edit-btn" onClick={() => handleEdit(account)}>
                  編集
                </button>
              )}
            </td>
          </tr>
        ))}
        
        {accounts.length > 0 && (
          <tr>
            <td colSpan="3"><strong>合計</strong></td>
            <td className="text-right text-red">
              <strong>{formatCurrency(totalWithdrawal)}</strong>
            </td>
            <td className="text-right text-blue">
              <strong>{formatCurrency(totalDeposit)}</strong>
            </td>
            <td className="text-right">
              <strong>{formatCurrency(finalBalance)}</strong>
            </td>
            <td className="col-remarks"></td>
            <td className="col-edit"></td>
          </tr>
        )}
      </tbody>
    </table>
  );
}

export default BankAccountTable;