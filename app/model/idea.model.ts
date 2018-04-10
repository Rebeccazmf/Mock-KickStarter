export class Idea {
    constructor(public ideaId=null,
                public ideaName:string,
                public ideaDesc:string,
                public targetAmount:number,
                public gatheredAmount:number,
                public startDate:Date,
                public endDate:Date,
                public ideaStatus:string,
                public userId:number,
                public categoryId:number){}
    
    // constructor(public ideaId=null,
    //             public ideaName:string,
    //             public ideaDesc:string,
    //             public targetAmount:number,
    //             public gatheredAmount:number,
    //             public startDate:Date,
    //             public endDate:Date,
    //             public ideaStatus:string,
    //             public userId:number,
    //             public categoryId:number
    //         ){
        
    // }

}
