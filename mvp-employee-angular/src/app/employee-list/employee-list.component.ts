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

  // employees: Observable<Employee[]>;
  page = 1;
  collectionSize = 10;
  pageSize = 30;
  employees: Employee[];
  title = 'modal2';

  // constructor(private employeeService: EmployeeService) {}
  constructor(private employeeService: EmployeeService, private fb: FormBuilder, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(): void {
    this.searchEmployees(-1, -1);
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

  searchEmployees(minSalary: any, maxSalary: any): void {
    if (!(typeof minSalary !== 'undefined' && minSalary !== '' && typeof maxSalary !== 'undefined' && maxSalary !== '')) {
      minSalary = -1;
      maxSalary = -1;
    }

    this.employeeService.searchEmployeesList(minSalary, maxSalary, (this.page - 1), this.pageSize).subscribe(
      data => {
        console.log(data);
        this.collectionSize = data.collectionSize;
        this.employees = data.results;
      },
      error => {
        console.log(error);
      });
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
