import { Component, OnInit } from '@angular/core';
import { NgbDateStruct, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { RecordService } from '../services/record.service';

@Component({
  selector: 'app-create-record',
  templateUrl: './create-record.component.html',
  styleUrls: ['./create-record.component.scss'],
})
export class CreateRecordComponent implements OnInit {
  model: NgbDateStruct;
  date: { year: number; month: number };
  constructor(
    private calendar: NgbCalendar,
    private recordService: RecordService
  ) {}

  ngOnInit(): void {}

  onSubmit(recordForm: NgForm) {
    if (!recordForm.value.email) {
      alert('Please enter a valid email address!');
    } else if (
      !this.isValidDate(recordForm.value.startDate) ||
      !this.isValidDate(recordForm.value.endDate) ||
      !this.isValidTime(recordForm.value.startTime) ||
      !this.isValidTime(recordForm.value.endTime)
    ) {
      alert('Please select a valid date and time!');
    } else {
      let startDate = this.getDate(
        recordForm.value.startDate,
        recordForm.value.startTime
      );
      let endDate = this.getDate(
        recordForm.value.endDate,
        recordForm.value.endTime
      );

      this.recordService
        .postRecord(recordForm.value.email, startDate, endDate)
        .subscribe((resp) => {
          alert('Record Added Successfully!');
        });
    }
  }
  getDate(dateObj: any, time: any) {
    let date = new Date();
    date.setFullYear(dateObj.year, dateObj.month - 1, dateObj.day);

    date.setHours(time.hour);
    date.setMinutes(time.minute);
    return date;
  }
  isValidDate(date: any) {
    if (!date || !date.year || !date.month || !date.day) {
      return false;
    }
    return true;
  }
  isValidTime(time: any) {
    if (!time || !time.hour || !time.minute) {
      return false;
    }
    return true;
  }
}
