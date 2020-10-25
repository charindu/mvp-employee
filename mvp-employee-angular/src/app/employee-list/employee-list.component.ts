import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Observable<Employee[]>;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(): void {
    this.employees = this.employeeService.getEmployeesList();
  }

  deleteEmployee(id: number, loginName: string): void {
    if (confirm('Are you sure, you want to delete ' + loginName + '?')) {
      this.employeeService.deleteEmployee(id)
        .subscribe(
          data => {
            console.log(data);
            this.reloadData();
          },
          error => {
            console.log(error);
          });
    }
  }

  searchEmployees(minSalary: any, maxSalary: any): void{
    if (minSalary !== '' && maxSalary !== ''){
      this.employeeService.searchEmployeesList(minSalary, maxSalary)
        .subscribe(
          data => {
            console.log(data);
            this.employees = data.results;
            //this.reloadData();
          },
          error => {
            console.log(error);
          });
    }
    if ((minSalary === '' && maxSalary === '')){
      this.reloadData();
    }
  }

  upload(): void{
    if (confirm('Do you want to continue to upload employees ?')) {
      this.employeeService.uploadEmployees()
        .subscribe(
          data => {
            console.log(data);
            this.reloadData();
          },
          error => {
            console.log(error);
          });
    }
  }
}
