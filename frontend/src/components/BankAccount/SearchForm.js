import React, { useState, useEffect, useRef } from 'react';

function SearchForm({ startDate, endDate, onSearch, onFileUpload, uploading }) {
  const [localStart, setLocalStart] = useState(startDate || '');
  const [localEnd,   setLocalEnd]   = useState(endDate   || '');
  const [file, setFile]             = useState(null);
  const fileInputRef = useRef(null);

  useEffect(() => { setLocalStart(startDate || ''); }, [startDate]);
  useEffect(() => { setLocalEnd(endDate   || ''); }, [endDate]);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSearch && onSearch({ startDate: localStart, endDate: localEnd });
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0] || null);
  };

  const handleUploadClick = (e) => {
    e.preventDefault();
    if (!file) {
      alert('ファイルを選択してください。');
      return;
    }
    onFileUpload && onFileUpload(file);
  };

  useEffect(() => {
    if (!uploading) {
      resetFileInput();
    }
  }, [uploading]);

  const resetFileInput = () => {
    setFile(null);
    if (fileInputRef.current) {
      fileInputRef.current.value = '';
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="startDate">開始日:</label>
          <input
            type="date"
            id="startDate"
            value={localStart}
            onChange={(e) => setLocalStart(e.target.value)}
          />
          <label htmlFor="endDate">終了日:</label>
          <input
            type="date"
            id="endDate"
            value={localEnd}
            onChange={(e) => setLocalEnd(e.target.value)}
          />
          <button type="submit">検索</button>
        </div>
      </form>

      <div className="form-group" style={{ marginTop: 20 }}>
        <label htmlFor="csvFile">CSVファイル:</label>
        <input
          type="file"
          id="csvFile"
          accept=".csv"
          onChange={handleFileChange}
          ref={fileInputRef}
          style={{ marginRight: 10 }}
        />
        <button
          onClick={handleUploadClick}
          disabled={!file || uploading}
        >
          {uploading ? 'アップロード中...' : 'アップロード'}
        </button>
        {file && !uploading && (
          <span style={{ marginLeft: 10 }}>選択: {file.name}</span>
        )}
      </div>
    </div>
  );
}

export default SearchForm;
