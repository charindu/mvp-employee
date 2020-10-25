import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EmployeeService } from './../employee.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})

export class EditEmployeeComponent implements OnInit{

  @Input() employee: any;
  @Input() employeeListComponent: any;

  title = 'modal2';
  editProfileForm: FormGroup;

  constructor(private fb: FormBuilder, public activeModal: NgbActiveModal, private employeeService: EmployeeService) {}

  ngOnInit(): void{
    this.editProfileForm = this.fb.group({
      employeeId: [this.employee.employeeId],
      name: [this.employee.name],
      loginName: [this.employee.loginName],
      salary: [this.employee.salary]
    });
  }

  closeModal(): void{
    this.activeModal.close();
    this.employeeListComponent.reloadData();
  }

  onSubmit(): void {

    this.editProfileForm.value.id = this.employee.id;

    this.employeeService.updateEmployee(this.employee.id, this.editProfileForm.value)
      .subscribe(
        data => {
          console.log(data);
          this.closeModal();
        },
        error => console.log(error));

  }
}
