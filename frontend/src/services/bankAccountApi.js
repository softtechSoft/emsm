import axios from 'axios';

const API_BASE_URL = '/emsm/bankAccount';

export const fetchAccounts = async (params = {}) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/search`, {
      params: {
        startDate: params.startDate || '',
        endDate: params.endDate || ''
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching accounts:', error);
    throw error;
  }
};

export const updateRemarks = async (id, remarks) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/updateRemarks`, {
      id: id,
      remarks: remarks
    });
    return response.data;
  } catch (error) {
    console.error('Error updating remarks:', error);
    throw error;
  }
};

export const uploadCsvFile = async (file) => {
  try {
    const formData = new FormData();
    formData.append('file', file);
    
    const response = await axios.post(`${API_BASE_URL}/upload`, formData);
    
    return response.data;
  } catch (error) {
    console.error('Error uploading CSV file:', error);
    throw error;
  }
};