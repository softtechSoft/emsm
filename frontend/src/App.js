import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import BankAccountPage from './pages/BankAccountPage';
// import ExpenseInfoPage from './pages/ExpenseInfoPage';  // 将来添加其他页面

function App() {
  return (
    <Routes>
      {/* 银行账户管理页面 */}
      <Route path="/bankAccount" element={<BankAccountPage />} />
      
      {/* 将来添加其他页面 */}
      {/* <Route path="/expenseInfo" element={<ExpenseInfoPage />} /> */}
      {/* <Route path="/otherModule" element={<OtherModulePage />} /> */}
      
      {/* 根路径重定向 - 可以根据需要调整 */}
      <Route path="/" element={<Navigate to="/bankAccount" replace />} />
      
      {/* 404 页面 - 空白或重定向 */}
      <Route path="*" element={<div></div>} />
    </Routes>
  );
}

export default App;