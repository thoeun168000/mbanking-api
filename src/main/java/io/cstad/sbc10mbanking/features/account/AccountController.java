package io.cstad.sbc10mbanking.features.account;

import io.cstad.sbc10mbanking.features.account.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{actNo}/softDelete")
    void softDeleteAccount(@PathVariable("actNo") String actNo) {
        accountService.softDeleteAccount(actNo);
    }

    @PatchMapping("/{alias}")
    AccountResponse updateByAlias(@PathVariable("alias") String alias,
                                  @RequestBody AccountUpdateRequest accountUpdateRequest) {
        return accountService.updateByAlias(alias, accountUpdateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{actNo}")
    void deleteByActNo(@PathVariable("actNo") String actNo) {
        accountService.deleteByActNo(actNo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{actNo}/transfer-limit")
    void updateTransferLimit(@PathVariable("actNo") String actNo,
                             @Valid  @RequestBody AccountTransferLimitRequest accountTransferLimitRequest) {
        accountService.updateTransferLimit(actNo, accountTransferLimitRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{actNo}/hide")
    void hideAccount(@PathVariable("actNo") String actNo) {
        accountService.hideAccount(actNo);
    }

    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable("actNo") String actNo,
                                  @Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameAccount(actNo, accountRenameRequest);
    }

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable("actNo") String actNo) {
        return accountService.findByActNo(actNo);
    }

    @GetMapping
    Page<AccountResponse> findList(
        @RequestParam(required = false, defaultValue = "0") int pageNumber,
        @RequestParam(required = false, defaultValue = "25") int pageSize
    ){
        return accountService.findList(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountResponse createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.createNew(accountCreateRequest);
    }

}
