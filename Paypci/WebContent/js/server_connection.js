(function ($) {
  var locked = false;
  var errorRetryCount = 0;
  var blockUiOptions = { message: "Oops! Could not reach the server!" };

  // change this function to adjust the exponential backoff delay
  function backoff(n) {
    return Math.pow(2, n) * 100;
  }

  $(function () {
    $( document ).ajaxError(function () {
      var req = this;

      errorRetryCount += 1;

      if (!locked) {
         locked = true;
         $.blockUI(blockUiOptions);
      }

      // retry to send the request...
      setTimeout(function () { $.ajax(req); }, backoff(errorRetryCount));
    }).ajaxSuccess(function () {
      locked && $.unblockUI();
      locked = false;
      errorRetryCount = 0;
    });
  });
})(jQuery);
