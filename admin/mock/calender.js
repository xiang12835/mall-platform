const data = {
  '2020-08-01': false,
  '2020-08-02': false,
  '2020-08-03': false,
  '2020-08-04': false,
  '2020-08-05': false,
  '2020-08-06': false,
  '2020-08-07': false,
  '2020-08-08': false,
  '2020-08-09': false,
  '2020-08-10': false,
  '2020-08-11': false,
  '2020-08-12': false,
  '2020-08-13': false,
  '2020-08-14': true,
  '2020-08-15': false,
  '2020-08-16': false,
  '2020-08-17': false,
  '2020-08-18': true,
  '2020-08-19': false,
  '2020-08-20': true,
  '2020-08-21': true,
  '2020-08-22': false,
  '2020-08-23': false,
  '2020-08-24': false,
  '2020-08-25': false,
  '2020-08-26': false,
  '2020-08-27': false,
  '2020-08-28': false,
  '2020-08-29': false,
  '2020-08-30': false,
  '2020-08-31': false,
}

module.exports = [

  // get user info
  {
    url: '/admin/v1/calender/data',
    type: 'get',
    response: config => {
      return {
        code: 20000,
        data: {
          item: data
        }
      }
    }
  }
]
