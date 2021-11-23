import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RecordService } from '../services/record.service';

const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent implements OnInit {

  constructor(private recordService: RecordService) { }
  
  recordList: {email: string; start: Date; end: Date }[] ;
  ngOnInit(): void {
  }

  onSubmit(searchForm: NgForm) {
    this.recordService.getRecords(searchForm.value.email).subscribe(resp => {
     
      this.recordList = resp as {email: string; start: Date; end: Date }[];
      this.recordList = this.recordList.filter(this.notEmpty);
      
    });
  
  }

  getRecordList(){
    return this.recordList;
  }

notEmpty<TValue>(value: TValue | null | undefined): value is TValue {
    return value !== null && value !== undefined;
}

 
}
