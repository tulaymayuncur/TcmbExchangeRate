import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from "primeng/api";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[MessageService]
})
export class AppComponent {

      selectedDate: string = "";
      exchangeRate: number | undefined;
      title = 'CurrencyExchangeApp';
      selectedSize: any;
      exchanges:any[] = [];
      constructor(public http:HttpClient,private messageService: MessageService) {
      }
      getExchangeRate() {
        console.log("date: ",this.selectedDate)
        if (this.selectedDate) {
          // API URL ve veri
          const apiUrl = 'http://localhost:8080/api/currencies/get';
          const day = this.selectedDate.split('-')[2].toString(); // Günü al ve stringe çevir

          const formattedDay = (day.length === 1) ? `0${day}` : day;
          const url = formattedDay + this.selectedDate.split('-')[1].toString() + this.selectedDate.split('-')[0].toString() + ".xml";
          const requestData = {
            url: url,
            date: this.selectedDate
          };

          const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Access-Control-Allow-Credentials': 'true',
          });

            this.http.post<any>(apiUrl, JSON.stringify(requestData), { headers: headers })
              .subscribe(response => {
                this.exchanges = response.data;
                this.showSuccess();
              }, error => {
                this.showError()
              });

    }

  }
  showError() {
    this.messageService.add({ severity: 'error', summary: 'Hata', detail: 'Belirtilen tarihe ait kur bilgisi yok' });
  }
  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Başarılı', detail: 'Kur bilgisi listelendi.' });
  }
}
