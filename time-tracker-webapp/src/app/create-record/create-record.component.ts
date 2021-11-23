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
  time = { hour: 13, minute: 30 };
  constructor(
    private calendar: NgbCalendar,
    private recordService: RecordService
  ) {}

  ngOnInit(): void {}

  onSubmit(recordForm: NgForm) {
    console.log(recordForm.value);

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
         alert("Record Added!");
          //this.recordList = resp as {email: string; start: Date; end: Date }[];
        })
  
  }
  getDate(dateObj: any, time: any) {
    let date = new Date();
    date.setFullYear(dateObj.year, dateObj.month - 1, dateObj.day);

    date.setHours(time.hour);
    date.setMinutes(time.minute);

    console.log(date);
    return date;
  }
}
