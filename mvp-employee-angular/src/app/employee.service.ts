import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getEmployee(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/users/${id}`);
  }

  createEmployee(id: number, employee: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/users/${id}`, employee);
  }

  updateEmployee(id: number, value: any): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/users/${id}`, value);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/users/${id}`);
  }

  searchEmployeesList(minSalary: number, maxSalary: number, page: number, limit: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/users?minSalary=${minSalary}&maxSalary=${maxSalary}&offset=${page}&limit=${limit}&sort=+name`);
  }

  getEmployeesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/allusers`);
  }

  uploadEmployees(): Observable<any> {
    return this.http.post(`${this.baseUrl}/users/upload`, null);
  }
}
