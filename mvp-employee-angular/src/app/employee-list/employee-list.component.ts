import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeService } from './../employee.service';
import { Employee } from './../employee';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {EditEmployeeComponent} from '../edit-employee/edit-employee.component';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})

export class EmployeeListComponent implements OnInit  {

  employees: Observable<Employee[]>;
  title = 'modal2';

  // constructor(private employeeService: EmployeeService) {}
  constructor(private employeeService: EmployeeService, private fb: FormBuilder, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(): void {
    this.employees = this.employeeService.getEmployeesList();
  }

  openModal(user): void {
    const modalRef = this.modalService.open(EditEmployeeComponent, {
      centered: true,
      backdrop: 'static'
    });

    modalRef.componentInstance.employee = user;
    modalRef.componentInstance.employeeListComponent = this;
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
