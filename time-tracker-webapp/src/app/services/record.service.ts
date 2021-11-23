import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BASE_URL } from '../globals';
const localUrl = 'assets/data/smartphone.json';

@Injectable({
  providedIn: 'root',
})
export class RecordService {
  constructor(private httpClient: HttpClient) {}

  getRecords(emailId: string) {
    let params = new HttpParams();
    params = params.append('email', emailId);
    params = params.append('offset', 0);
    params = params.append('length', 10);
    return this.httpClient.get(BASE_URL, { params: params });
  }

  postRecord(emailId: string, startDateTime: Date, endDateTime: Date) {
    let params = new HttpParams();
    console.log(startDateTime.toUTCString())
    console.log(endDateTime.toUTCString())
    var req = {
      email: emailId,
      start: startDateTime.toISOString(),
      end: endDateTime.toISOString()
    };
    console.log(req);
  
    
    return this.httpClient.post(BASE_URL, req);
  }
}
