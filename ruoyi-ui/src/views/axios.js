import axios from 'axios'
 const service = axios.create({
   baseURL: 'http://localhost:8080' // API 的基础路径


 });

 export default service;
