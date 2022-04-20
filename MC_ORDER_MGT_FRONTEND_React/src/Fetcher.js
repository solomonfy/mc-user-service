const checkStatus = (response) => {
  if (response.ok) {
    return response;
  } else {
    const error = new Error(response.statusText);
    error.response = response;
    throw error;
  }
};

const parseJSON = (resp) => resp.json();

const Fetcher = {
  get: (path, params) => fetch(path, params).then(checkStatus).then(parseJSON),

  // post: (path, params) =>
  // fetch(path, params)
  //     .then(checkStatus)
};

export { Fetcher };


//on the component using this,

// import { Fetcher } from "./Fetcher";

    // Fetcher.get(productsUrl).then((resp) => {
    //   setAppState(resp.data.products);
    //   console.log(resp.data.products);
    // });

       // OR

    // (async function () {
    //   const data =  await Fetcher.get(productsUrl);
    //   console.log(data);
    //   setAppState(data);
    // })();