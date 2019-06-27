import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";


@Injectable()
export class AgentService {

    constructor(private http: HttpClient) { }

    upgrade() {
        return this.http.put("/api/authservice/agents/upgrade", null);
    }


}

